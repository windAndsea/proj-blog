package com.bandit.blog.system.mapper;

import com.bandit.blog.entities.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<String> selectRoleIdsByUserId(@Param("userId") String userId);

    /**
     * 通过用户ID删除用户角色关系
     *
     * @param userId 用户ID
     * @return 结果
     */
    boolean deleteUserRoleByUserId(@Param("userId") String userId);

    /**
     * 新增用户角色关系
     *
     * @param userId 用户ID
     * @param roleIds 角色ID集合
     * @return 结果
     */
    boolean saveUserRole(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}
