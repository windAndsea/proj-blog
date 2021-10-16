package com.bandit.blog.article.controller;


import com.bandit.blog.article.req.AdvertReq;
import com.bandit.blog.article.service.IAdvertService;
import com.bandit.blog.entities.Advert;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("删除广告信息")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    public Result deleteAdvert(@PathVariable("id") String id) {
        return advertService.deleteAdvertById(id);
    }

    @ApiOperation("查询广告详情")
    @GetMapping("/view/{id}")
    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    public Result viewAdvert(@PathVariable("id") String id) {
        return Result.ok(advertService.getById(id));
    }

    @PostMapping("/modify")
    @ApiOperation("修改广告信息")
    public Result modifyAdvert(@RequestBody Advert advert) {
        return advertService.modifyAdvert(advert);
    }

    @PostMapping("/add")
    @ApiOperation("新增广告信息")
    public Result addAdvert(@RequestBody Advert advert) {
        advertService.save(advert);
        return Result.ok();
    }

}
