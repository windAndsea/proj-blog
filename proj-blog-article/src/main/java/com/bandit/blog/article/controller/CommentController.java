package com.bandit.blog.article.controller;


import com.bandit.blog.article.service.ICommentService;
import com.bandit.blog.entities.Comment;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-15
 */
@Api(tags = "评论管理接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("删除文章评论")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "评论ID", required = true)
    public Result deleteCommentsById(@PathVariable("id") String id) {
        return commentService.deleteById(id);
    }

    @ApiOperation(("新增评论"))
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return Result.ok();
    }
}
