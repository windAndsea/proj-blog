package com.bandit.blog.feign;

import com.bandit.blog.entities.SysMenu;
import com.bandit.blog.entities.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "system-server", path = "/system")  // 目标微服务
public interface IFeignSystemController {
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation("Feign接口-通过用户名查询用户信息")
    @GetMapping("/api/feign/user/{username}")
    SysUser findUserByUsername(@PathVariable("username") String username);

    @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
    @ApiOperation("Feign接口-通过用户ID查询菜单信息")
    @GetMapping("/api/feign/menu/{userId}")
    List<SysMenu> findMenuByUserId(@PathVariable("userId") String userId);
}
