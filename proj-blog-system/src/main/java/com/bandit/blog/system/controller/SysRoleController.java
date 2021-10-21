package com.bandit.blog.system.controller;

import com.bandit.blog.entities.SysRole;
import com.bandit.blog.system.req.SysRoleReq;
import com.bandit.blog.system.service.ISysRoleService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("查询角色")
    @PostMapping("/search")
    public Result searchRole(@RequestBody SysRoleReq sysRoleReq) {
        return sysRoleService.queryPage(sysRoleReq);
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/add")
    public Result addRole(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @ApiOperation("查询角色详情")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @GetMapping("/view/{id}")
    public Result viewRole(@PathVariable("id") String id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("修改角色信息")
    @PostMapping("/modify")
    public Result updateRole(@RequestBody SysRole sysRole) {
        sysRole.setUpdateDate(new Date());
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    @ApiOperation("通过ID删除角色及其角色菜单关系")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @PostMapping("/delete/{id}")
    public Result deleteRole(@PathVariable("id") String id) {
        return sysRoleService.deleteRole(id);
    }

    @ApiOperation("通过角色ID查询其所拥有的菜单ID集合")
    @ApiImplicitParam(name = "roleId", value = "角色ID", required = true)
    @PostMapping("/{roleId}/menu/ids")
    public Result selectMenuIdsByRoleId(@PathVariable("roleId") String roleId) {
        return sysRoleService.selectMenuIdsByRoleId(roleId);
    }

    @ApiOperation("新增角色菜单关系数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true),
            @ApiImplicitParam(allowMultiple = true, dataType = "String", name = "menuIds", value = "菜单ID集合", required
                    = true)
    })
    @PostMapping("/{roleId}/menu/add")
    public Result addRoleMenu(@PathVariable("roleId") String roleId, @RequestBody List<String> menuIds) {
        return sysRoleService.saveRoleMenu(roleId, menuIds);
    }
}
