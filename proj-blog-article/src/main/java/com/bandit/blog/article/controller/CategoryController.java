package com.bandit.blog.article.controller;

import com.bandit.blog.article.req.CategoryReq;
import com.bandit.blog.article.service.ICategoryService;
import com.bandit.blog.entities.Category;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分类管理接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("根据分类名称与状态分页查询分类信息")
    @PostMapping("/search")
    public Result searchCategory(@RequestBody CategoryReq categoryReq) {
        Result result = categoryService.queryPage(categoryReq);
        return result;
    }

    @ApiOperation("查询分类详情")
    @ApiImplicitParam(name = "id", value = "类别ID", required = true)
    @GetMapping("/view/{id}")
    public Result viewCategory(@PathVariable("id") String id) {
        Category category = categoryService.getById(id);
        return Result.ok(category);
    }

    @ApiOperation("修改类别信息")
    @PostMapping("/modify")
    public Result updateCategory(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.ok();
    }

    @ApiOperation("新增分类信息")
    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Result.ok();
    }

    @ApiOperation("删除分类信息")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "类别ID", required = true)
    public Result deleteCategory(@PathVariable("id") String id) {
        categoryService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("查询正常使用的分类信息")
    @GetMapping("/list")
    public Result queryNormalCategory() {
        return categoryService.findAllNormal();
    }

    @ApiOperation("查询正常使用的分类及标签信息")
    @GetMapping("/label/list")
    public Result findAllNormalCategoryAndLabel() {
        return categoryService.findAllNormalCategoryAndLabel();
    }
}
