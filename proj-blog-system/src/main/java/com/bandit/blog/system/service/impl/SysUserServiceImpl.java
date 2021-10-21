package com.bandit.blog.system.service.impl;

import com.bandit.blog.entities.SysUser;
import com.bandit.blog.feign.IFeignArticleController;
import com.bandit.blog.feign.IFeignQuestionController;
import com.bandit.blog.feign.req.UserInfoReq;
import com.bandit.blog.system.mapper.SysUserMapper;
import com.bandit.blog.system.req.RegisterReq;
import com.bandit.blog.system.req.SysUserCheckPasswordReq;
import com.bandit.blog.system.req.SysUserReq;
import com.bandit.blog.system.req.SysUserUpdatePasswordReq;
import com.bandit.blog.system.service.ISysUserService;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IFeignArticleController feignArticleController;

    @Autowired
    private IFeignQuestionController feignQuestionController;

    @Override
    public Result queryPage(SysUserReq sysUserReq) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotEmpty(sysUserReq.getUsername())) {
            lambdaQueryWrapper.like(SysUser::getUsername, sysUserReq.getUsername());
        }

        if (StringUtils.isNotEmpty(sysUserReq.getMobile())) {
            lambdaQueryWrapper.like(SysUser::getMobile, sysUserReq.getMobile());
        }

        return Result.ok(baseMapper.selectPage(sysUserReq.getPage(), lambdaQueryWrapper));
    }

    @Override
    public Result queryRoleIdsByUserId(String userId) {
        return Result.ok(baseMapper.selectRoleIdsByUserId(userId));
    }

    @Transactional
    @Override
    public Result saveUserRole(String userId, List<String> roleIds) {
        baseMapper.deleteUserRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(userId, roleIds);
        }
        return Result.ok();
    }

    @Override
    public Result deleteById(String id) {
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            return Result.error("the user is not exist");
        }
        sysUser.setIsEnabled(0);
        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }

    @Override
    public Result checkPassword(SysUserCheckPasswordReq req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("userId cannot be empty");
        }

        if (StringUtils.isEmpty(req.getOldPassword())) {
            return Result.error("old password cannot be empty");
        }

        SysUser sysUser = baseMapper.selectById(req.getUserId());
        if (sysUser == null) {
            return Result.error("the user is not exist");
        }
        boolean checkRes = passwordEncoder.matches(req.getOldPassword(), sysUser.getPassword());
        if (!checkRes) {
            return Result.error("old password is not match");
        }
        return Result.ok();
    }

    @Override
    public Result updatePassword(SysUserUpdatePasswordReq req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("userId cannot be empty");
        }

        if (StringUtils.isEmpty(req.getNewPassword())) {
            return Result.error("new password cannot be empty");
        }

        if (StringUtils.isEmpty(req.getConfirmPassword())) {
            return Result.error("confirm password cannot be empty");
        }

        if (StringUtils.equals(req.getNewPassword(), req.getConfirmPassword())) {
            return Result.error("new password not equals to confirm password");
        }

        SysUser sysUser = baseMapper.selectById(req.getUserId());
        if (sysUser == null) {
            return Result.error("the user is not exist");
        }

        if (StringUtils.isNotEmpty(req.getOldPassword()) && !passwordEncoder.matches(req.getOldPassword(),
                sysUser.getPassword())) {
            return Result.error("old password is not match");
        }

        sysUser.setPassword(passwordEncoder.encode(req.getNewPassword()));
        sysUser.setPwdUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }

    @Override
    public Result updateUser(SysUser sysUser) {
        // 1，查询用户信息
        SysUser dbUser = baseMapper.selectById(sysUser.getId());

        // 2，判断用户昵称、头像是否变更
        if (StringUtils.equals(dbUser.getNickName(), sysUser.getNickName())
                || StringUtils.equals(dbUser.getImageUrl(), sysUser.getImageUrl())) {
            // 调用微服务接口
            UserInfoReq userInfoReq = new UserInfoReq(sysUser.getId(), sysUser.getNickName(), sysUser.getImageUrl());
            feignArticleController.updateUserInfo(userInfoReq);
            feignQuestionController.updateUserInfo(userInfoReq);
        }

        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }

    @Override
    public Result countUserTotal() {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getIsEnabled, 1);
        return Result.ok(baseMapper.selectCount(lambdaQueryWrapper));
    }

    @Override
    public Result checkUsernameExist(String username) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = baseMapper.selectOne(lambdaQueryWrapper);
        if (sysUser != null) {
            return Result.ok(true);
        }
        return Result.ok(false);
    }

    @Override
    public Result register(RegisterReq registerReq) {
        if (StringUtils.isEmpty(registerReq.getUsername())) {
            return Result.error("username cannot be empty");
        }

        if (StringUtils.isEmpty(registerReq.getPassword())) {
            return Result.error("password cannot be empty");
        }

        if (StringUtils.isEmpty(registerReq.getConfirmPassword())) {
            return Result.error("confirm password cannot be empty");
        }

        if (StringUtils.equals(registerReq.getPassword(), registerReq.getConfirmPassword())) {
            return Result.error("password not equals to confirm password");
        }

        if ((Boolean) checkUsernameExist(registerReq.getUsername()).getData()) {
            return Result.error("username has bean register");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerReq.getUsername());
        sysUser.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        this.save(sysUser);
        return Result.ok();
    }

    @Override
    public SysUser findByUsername(String username) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getUsername, username);
        SysUser sysUser = baseMapper.selectOne(lambdaQueryWrapper);
        return sysUser;
    }
}
