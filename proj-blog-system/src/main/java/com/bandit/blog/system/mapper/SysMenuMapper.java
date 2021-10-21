package com.bandit.blog.system.mapper;

import com.bandit.blog.entities.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过用户ID查询用户菜单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<SysMenu> queryUserMenu(@Param("userId") String userId);
}
