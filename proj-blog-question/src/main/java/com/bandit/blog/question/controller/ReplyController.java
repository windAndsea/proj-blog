package com.bandit.blog.question.controller;

import com.bandit.blog.entities.Reply;
import com.bandit.blog.question.service.IReplyService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 回答信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Api(tags = "回答管理接口")
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("删除回复")
    @PostMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "回复ID", required = true)
    public Result deleteById(@PathVariable("id") String id) {
        return replyService.deleteById(id);
    }

    @ApiOperation("新增回复")
    @PostMapping("/save")
    public Result addReply(@RequestBody Reply reply) {
        return replyService.addReply(reply);
    }
}
