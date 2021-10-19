package com.bandit.blog.system.controller;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.system.req.SysMenuReq;
import com.bandit.blog.system.service.ISysMenuService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("查询菜单")
    @PostMapping("/search")
    public Result searchMenu(@RequestBody SysMenuReq sysMenuReq) {
        return sysMenuService.queryList(sysMenuReq);
    }

    @ApiOperation("通过菜单ID删除菜单及其子菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true)
    @PostMapping("/delete/{id}")
    public Result deleteMenu(@PathVariable("id") String id) {
        return sysMenuService.deleteById(id);
    }

    @ApiOperation("新增菜单信息")
    @PostMapping("/add")
    public Result addMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("查询菜单详情")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    @GetMapping("/view/{id}")
    public Result viewMenu(@PathVariable("id") String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    @ApiOperation("修改菜单信息")
    @PostMapping("/modify")
    public Result updateMenu(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateDate(new Date());
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }
}
