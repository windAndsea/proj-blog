package com.bandit.blog.system.service;

import com.bandit.blog.entities.SysUser;
import com.bandit.blog.system.req.RegisterReq;
import com.bandit.blog.system.req.SysUserCheckPasswordReq;
import com.bandit.blog.system.req.SysUserReq;
import com.bandit.blog.system.req.SysUserUpdatePasswordReq;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 用户信息分页查询
     *
     * @param sysUserReq 请求参数
     * @return 结果
     */
    Result queryPage(SysUserReq sysUserReq);

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    Result queryRoleIdsByUserId(String userId);

    /**
     * 新增用户角色关系
     *
     * @param userId  用户ID
     * @param roleIds 角色ID集合
     * @return 结果
     */
    Result saveUserRole(String userId, List<String> roleIds);

    /**
     * 通过用户ID删除用户信息（软删除）
     *
     * @param id 用户ID
     * @return 结果
     */
    Result deleteById(String id);

    /**
     * 用户密码检查
     *
     * @param req 请求参数
     * @return 结果
     */
    Result checkPassword(SysUserCheckPasswordReq req);

    /**
     * 用户密码更新
     *
     * @param req 请求参数
     * @return 结果
     */
    Result updatePassword(SysUserUpdatePasswordReq req);

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    Result updateUser(SysUser sysUser);

    /**
     * 统计用户数
     *
     * @return 结果
     */
    Result countUserTotal();

    /**
     * 校验用户名是否存在
     *
     * @param username 用户名
     * @return 结果
     */
    Result checkUsernameExist(String username);

    /**
     * 用户注册
     *
     * @param registerReq 请求参数
     * @return 结果
     */
    Result register(RegisterReq registerReq);

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    SysUser findByUsername(String username);
}
