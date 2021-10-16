package com.bandit.blog.article.controller;


import com.bandit.blog.article.req.AdvertReq;
import com.bandit.blog.article.service.IAdvertService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@RestController
@RequestMapping("/advert")
@Api(tags = "广告管理接口")
public class AdvertController {
    @Autowired
    private IAdvertService advertService;

    @PostMapping("/search")
    @ApiOperation("分页查询广告信息")
    public Result searchAdvert(@RequestBody AdvertReq advertReq) {
        return advertService.queryPage(advertReq);
    }
}
