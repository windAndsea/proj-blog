package com.bandit.blog.article.controller;


import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.article.service.IArticleService;
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
 * 文章信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-12
 */
@Api(tags = "文章管理接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("分页查询文章信息")
    @PostMapping("/search")
    public Result queryArticle(@RequestBody ArticleReq articleReq) {
        return articleService.queryPage(articleReq);
    }
}
