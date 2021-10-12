package com.bandit.blog.article.controller;


import com.bandit.blog.article.req.LabelReq;
import com.bandit.blog.article.service.ILabelService;
import com.bandit.blog.entities.Category;
import com.bandit.blog.entities.Label;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-11
 */
@Api(tags = "标签管理接口")
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private ILabelService labelService;

    @ApiOperation("根据标签名称与分类分页查询标签信息")
    @PostMapping("/search")
    public Result searchLabel(@RequestBody LabelReq labelReq) {
        Result result = labelService.queryPage(labelReq);
        return result;
    }

    @ApiOperation("查询标签详情")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    @GetMapping("/view/{id}")
    public Result viewLabel(@PathVariable("id") String id) {
        Label label = labelService.getById(id);
        return Result.ok(label);
    }

    @ApiOperation("修改标签信息")
    @PostMapping("/modify")
    public Result updateLabel(@RequestBody Label label) {
        labelService.updateById(label);
        return Result.ok();
    }

    @ApiOperation("新增标签信息")
    @PostMapping("/add")
    public Result addLabel(@RequestBody Label label) {
        labelService.save(label);
        return Result.ok();
    }

    @ApiOperation("删除标签信息")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    public Result deleteLabel(@PathVariable("id") String id) {
        labelService.removeById(id);
        return Result.ok();
    }

}
