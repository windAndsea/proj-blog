package com.bandit.blog.article.api.controller;

import com.bandit.blog.article.req.ArticleListReq;
import com.bandit.blog.article.service.IArticleService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 此控制层接口，无需身份认证
 */
@Api(tags = "文章管理api接口")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("查询文章及标签信息-公开api接口")
    @GetMapping("/view/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result findArticleAndLabelById(@PathVariable("id") String id) {
        return articleService.findArticleAndLabelById(id);
    }

    @ApiOperation("更新文章浏览次数-公开api接口")
    @PostMapping("/view/count/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result updateViewCount(@PathVariable("id") String id) {
        return articleService.updateViewCount(id);
    }

    @ApiOperation("查询公开且审核通过的文章信息-公开api接口")
    @PostMapping("/list")
    public Result findListByCategoryIdAndLabelId(@RequestBody ArticleListReq articleListReq) {
        return articleService.findListByCategoryIdAndLabelId(articleListReq);
    }
}
