package com.bandit.blog.article.controller;


import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.article.req.ArticleUserReq;
import com.bandit.blog.article.service.IArticleService;
import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("查询文章及标签信息")
    @GetMapping("/view/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result findArticleAndLabelById(@PathVariable("id") String id) {
        return articleService.findArticleAndLabelById(id);
    }

    @ApiOperation("修改文章信息")
    @PostMapping("/modify")
    public Result updateArticle(@RequestBody Article article) {
        return articleService.saveOrUpdateArticle(article);
    }

    @ApiOperation("新增文章信息")
    @PostMapping("/add")
    public Result addArticle(@RequestBody Article article) {
        return articleService.saveOrUpdateArticle(article);
    }

    @ApiOperation("删除文章信息")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result deleteArticle(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.DELETE);
    }

    @ApiOperation("文章审核通过")
    @PostMapping("/audit/pass/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result auditPass(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.PASS);
    }

    @ApiOperation("文章审核失败")
    @PostMapping("/audit/reject/{id}")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    public Result auditReject(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.REJECT);
    }

    @ApiOperation("根据用户ID查询文章")
    @PostMapping("/user")
    public Result findListByUserId(@RequestBody ArticleUserReq articleUserReq) {
        return articleService.findListByUserId(articleUserReq);
    }

    @ApiOperation("更新点赞数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true),
            @ApiImplicitParam(name = "count", value = "点赞数", required = true)
    })
    @PostMapping("/thumb/{id}/{count}")
    public Result updateThumbHup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return articleService.updateThumbHup(id, count);
    }

    @ApiOperation("统计审核通过并公开的文章记录数")
    @GetMapping("/total")
    public Result countArticleRecord() {
        return articleService.countArticleRecord();
    }
}
