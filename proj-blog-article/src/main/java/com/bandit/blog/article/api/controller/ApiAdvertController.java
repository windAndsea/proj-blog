package com.bandit.blog.article.api.controller;


import com.bandit.blog.article.service.IAdvertService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/advert")
@Api(tags = "广告管理api接口")
public class ApiAdvertController {
    @Autowired
    private IAdvertService advertService;

    @ApiOperation("展示广告")
    @GetMapping("/show/{position}")
    @ApiImplicitParam(name = "position", value = "展示位置编号", required = true)
    public Result showAdvert(@PathVariable("position") int position) {
        return advertService.findByPosition(position);
    }
}
