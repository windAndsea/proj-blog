package com.bandit.blog.article.api.controller;

import com.bandit.blog.article.service.ICategoryService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此控制层接口，无需身份认证
 */
@Api(tags = "分类管理api接口")
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("查询正常使用的分类信息-公开api接口")
    @GetMapping("/list")
    public Result queryNormalCategory() {
        return categoryService.findAllNormal();
    }

    @ApiOperation("查询正常使用的分类及标签信息-公开api接口")
    @GetMapping("/label/list")
    public Result findAllNormalCategoryAndLabel() {
        return categoryService.findAllNormalCategoryAndLabel();
    }
}
