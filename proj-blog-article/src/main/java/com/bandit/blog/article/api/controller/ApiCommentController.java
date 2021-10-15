package com.bandit.blog.article.api.controller;

import com.bandit.blog.article.service.ICommentService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "评论管理api接口")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("查看文章评论")
    @GetMapping("/list/{articleId}")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    public Result findCommentsByArticleId(@PathVariable("articleId") String articleId) {
        return commentService.findByArticleId(articleId);
    }

}
