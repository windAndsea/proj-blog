package com.bandit.blog.system.mapper;

import com.bandit.blog.entities.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过角色ID删除角色菜单关系表记录
     *
     * @param roleId 角色ID
     * @return 结果
     */
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 通过角色ID查询其所拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 结果
     */
    List<String> selectMenuIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 新增角色菜单关系数据
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     * @return 结果
     */
    boolean insertRoleMenu(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);
}
