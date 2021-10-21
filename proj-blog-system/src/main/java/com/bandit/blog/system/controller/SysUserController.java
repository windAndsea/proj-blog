package com.bandit.blog.system.controller;

import com.bandit.blog.entities.SysUser;
import com.bandit.blog.system.req.SysUserCheckPasswordReq;
import com.bandit.blog.system.req.SysUserReq;
import com.bandit.blog.system.req.SysUserUpdatePasswordReq;
import com.bandit.blog.system.service.ISysUserService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-19
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("查询用户信息")
    @PostMapping("/search")
    public Result searchUser(@RequestBody SysUserReq sysUserReq) {
        return sysUserService.queryPage(sysUserReq);
    }

    @ApiOperation("通过用户ID查询角色ID集合")
    @GetMapping("/{id}/role/ids")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    public Result queryRoleIdsByUserId(@PathVariable("id") String id) {
        return sysUserService.queryRoleIdsByUserId(id);
    }

    @ApiOperation("新增用户角色关系")
    @PostMapping("/{userId}/role/add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true),
            @ApiImplicitParam(allowMultiple = true, dataType = "String", name = "roleIds", value = "角色ID集合", required = true)
    })
    public Result saveUserRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {
        return sysUserService.saveUserRole(userId, roleIds);
    }

    @ApiOperation("删除用户信息")
    @GetMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    public Result deleteUser(@PathVariable("id") String id) {
        return sysUserService.deleteById(id);
    }

    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public Result saveUser(@RequestBody SysUser sysUser) {
        String encryptPwd = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encryptPwd);
        sysUserService.save(sysUser);
        return Result.ok();
    }

    @ApiOperation("查询用户详情")
    @GetMapping("/view/{id}")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    public Result viewUser(@PathVariable("id") String id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    @ApiOperation("用户密码检查")
    @PostMapping("/check/password")
    public Result checkPassword(@RequestBody SysUserCheckPasswordReq sysUserCheckPasswordReq) {
        return sysUserService.checkPassword(sysUserCheckPasswordReq);
    }

    @ApiOperation("用户密码更新")
    @PostMapping("/modify/password")
    public Result updatePassword(@RequestBody SysUserUpdatePasswordReq sysUserUpdatePasswordReq) {
        return sysUserService.updatePassword(sysUserUpdatePasswordReq);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/modify")
    public Result updateUser(@RequestBody SysUser sysUser) {
        return sysUserService.updateUser(sysUser);
    }

    @ApiOperation("统计用户数")
    @GetMapping("/total")
    public Result countUserTotal() {
        return sysUserService.countUserTotal();
    }
}
