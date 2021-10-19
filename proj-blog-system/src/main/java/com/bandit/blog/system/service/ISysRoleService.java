package com.bandit.blog.system.service;

import com.bandit.blog.entities.SysRole;
import com.bandit.blog.system.req.SysRoleReq;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 分页查询角色
     *
     * @param sysRoleReq 请求参数
     * @return 结果
     */
    Result queryPage(SysRoleReq sysRoleReq);

    /**
     * 通过角色ID删除角色信息及对应的角色菜单关系
     *
     * @param roleId 角色ID
     * @return 结果
     */
    Result deleteRole(String roleId);

    /**
     * 通过角色ID查询其所拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 结果
     */
    Result selectMenuIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 新增角色菜单关系数据
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return 结果
     */
    Result saveRoleMenu(String roleId, List<String> menuIds);
}
