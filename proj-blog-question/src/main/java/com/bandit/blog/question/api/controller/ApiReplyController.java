package com.bandit.blog.question.api.controller;

import com.bandit.blog.question.service.IReplyService;
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
 * 回答信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Api(tags = "回答管理Api接口")
@RestController
@RequestMapping("/api/reply")
public class ApiReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("查询问题回复")
    @GetMapping("/list/{questionId}")
    @ApiImplicitParam(name = "questionId", value = "问题ID", required = true)
    public Result findByQuestionId(@PathVariable("questionId") String questionId) {
        return replyService.findByQuestionId(questionId);
    }
}
