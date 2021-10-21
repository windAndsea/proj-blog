package com.bandit.blog.system.service;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.system.req.SysMenuReq;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 查询菜单列表
     *
     * @param sysMenuReq 查询条件
     * @return 结果
     */
    Result queryList(SysMenuReq sysMenuReq);

    /**
     * 根据菜单ID删除菜单
     *
     * @param id 菜单ID
     * @return 结果
     */
    Result deleteById(String id);

    /**
     * 通过用户ID查询用户菜单树
     *
     * @param userId 用户ID
     * @return 结果
     */
    Result findUserMenuTree(String userId);
}
