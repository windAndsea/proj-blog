package com.bandit.blog.system.api.controller;

import com.bandit.blog.system.req.RegisterReq;
import com.bandit.blog.system.service.ISysUserService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户管理api接口")
@RequestMapping("/api/user")
public class ApiSysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("校验用户名是否存在")
    @GetMapping("/check/username/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    public Result checkUsernameExist(@PathVariable String username) {
        return sysUserService.checkUsernameExist(username);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterReq registerReq) {
        return sysUserService.register(registerReq);
    }
}
