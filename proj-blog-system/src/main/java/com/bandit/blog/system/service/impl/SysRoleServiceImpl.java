package com.bandit.blog.system.service.impl;

import com.bandit.blog.entities.SysRole;
import com.bandit.blog.system.mapper.SysRoleMapper;
import com.bandit.blog.system.req.SysRoleReq;
import com.bandit.blog.system.service.ISysRoleService;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Override
    public Result queryPage(SysRoleReq sysRoleReq) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysRoleReq.getName())) {
            queryWrapper.like("name", sysRoleReq.getName());
        }

        queryWrapper.orderByDesc("update_date");

        return Result.ok(baseMapper.selectList(queryWrapper));
    }

    @Transactional
    @Override
    public Result deleteRole(String roleId) {
        baseMapper.deleteRoleMenuByRoleId(roleId);
        baseMapper.deleteById(roleId);
        return Result.ok();
    }

    @Override
    public Result selectMenuIdsByRoleId(String roleId) {
        return Result.ok(baseMapper.selectMenuIdsByRoleId(roleId));
    }

    @Transactional
    @Override
    public Result saveRoleMenu(String roleId, List<String> menuIds) {
        baseMapper.deleteRoleMenuByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(menuIds)) {
            baseMapper.insertRoleMenu(roleId, menuIds);
        }

        return Result.ok();
    }
}
