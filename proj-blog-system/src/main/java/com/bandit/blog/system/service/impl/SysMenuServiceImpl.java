package com.bandit.blog.system.service.impl;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.system.mapper.SysMenuMapper;
import com.bandit.blog.system.req.SysMenuReq;
import com.bandit.blog.system.service.ISysMenuService;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ResultEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public Result queryList(SysMenuReq sysMenuReq) {
        // 1，条件查询菜单
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(sysMenuReq.getName())) {
            /*
             *  TODO name值为空时，能满足预期，当name值非空时，该处获取的数据跟接口需求目标不符，存疑
             *  另，该处可以采用自定义mapper接口方式进行处理
             */
            queryWrapper.like("name", sysMenuReq.getName());
        }

        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByDesc("update_date");

        List<SysMenu> sysMenuList = baseMapper.selectList(queryWrapper);

        // 2，数据整合
        List<SysMenu> data = this.buildTreeData(sysMenuList);
        return Result.ok(data);
    }

    /**
     * 构建树形菜单数据
     *
     * @param sysMenuList 所有查询到的菜单集合
     * @return 结果
     */
    private List<SysMenu> buildTreeData(List<SysMenu> sysMenuList) {
        // 过滤获取根菜单
        List<SysMenu> rootMenuList = sysMenuList
                .stream()
                .filter(item -> StringUtils.equals(item.getParentId(), "0"))
                .collect(Collectors.toList());

        for (SysMenu menu : rootMenuList) {
            this.buildChildrenTreeData(sysMenuList, menu);
        }

        return rootMenuList;
    }

    /**
     * 递归获取子菜单
     *
     * @param sysMenuList 所有查询到的菜单集合
     * @param menu 子菜单对象
     */
    private void buildChildrenTreeData(List<SysMenu> sysMenuList, SysMenu menu) {
        // 存放子菜单
        List<SysMenu> children = new ArrayList<>();
        for (SysMenu obj : sysMenuList) {
            if (StringUtils.equals(obj.getParentId(), menu.getId())) {
                children.add(obj);
                this.buildChildrenTreeData(sysMenuList, obj);
            }
        }

        menu.setChildren(children);
    }

    @Transactional
    @Override
    public Result deleteById(String id) {
        baseMapper.deleteById(id);
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysMenu::getParentId, id);
        baseMapper.delete(lambdaQueryWrapper);
        return Result.ok();
    }

    @Override
    public Result findUserMenuTree(String userId) {
        List<SysMenu> menuList = baseMapper.queryUserMenu(userId);
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0) == null) {
            return Result.build(ResultEnum.SUCCESS);
        }

        List<SysMenu> dirMenuList = new ArrayList<>();
        List<String> buttonList = new ArrayList<>();

        for (SysMenu menu: menuList) {
            if (menu.getType() == 1 || menu.getType() == 2) {
                dirMenuList.add(menu);
                continue;
            }
            buttonList.add(menu.getCode());
        }

        List<SysMenu> menuTreeList = this.buildTreeData(dirMenuList);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("menuTreeList", menuTreeList);
        resMap.put("buttonList", buttonList);
        return Result.ok(resMap);
    }
}
