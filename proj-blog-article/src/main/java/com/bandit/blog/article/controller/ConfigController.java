package com.bandit.blog.article.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "配置中心管理接口")
@RequestMapping("/config")
@RestController
@RefreshScope
public class ConfigController {
    @Value("${user.name}")
    private String name;

    @ApiOperation("展示获取到的配置信息")
    @GetMapping("/show")
    public String printConfig() {
        System.out.println(name);
        return name;
    }
}
