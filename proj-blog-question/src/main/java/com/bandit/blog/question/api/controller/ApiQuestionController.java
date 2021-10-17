package com.bandit.blog.question.api.controller;

import com.bandit.blog.entities.Question;
import com.bandit.blog.question.service.IQuestionService;
import com.bandit.blog.util.base.BaseRequest;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Api(tags = "问答管理api接口")
@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("查询热门问题")
    @PostMapping("/hot")
    public Result queryHotQuestion(@RequestBody BaseRequest<Question> baseRequest) {
        return questionService.findHotQuestion(baseRequest);
    }

    @ApiOperation("查询最新问题")
    @PostMapping("/latest")
    public Result queryLatestQuestion(@RequestBody BaseRequest<Question> baseRequest) {
        return questionService.findLatestQuestion(baseRequest);
    }

    @ApiOperation("查询等待回答的问题")
    @PostMapping("/wait")
    public Result queryWaitReplyQuestion(@RequestBody BaseRequest<Question> baseRequest) {
        return questionService.findWaitReplyQuestion(baseRequest);
    }

    @ApiOperation("通过标签ID查询问题")
    @PostMapping("/list/{labelId}")
    @ApiImplicitParam(name = "labelId", value = "标签ID", required = true)
    public Result queryQuestionByLabelId(@PathVariable("labelId") String labelId,
                                         @RequestBody BaseRequest<Question> baseRequest) {
        return questionService.findQuestionByLabelId(baseRequest, labelId);
    }

    @ApiOperation("通过问题ID查询问题详情")
    @GetMapping("/view/{id}")
    @ApiImplicitParam(name = "id", value = "问题ID", required = true)
    public Result viewQuestion(@PathVariable("id") String id) {
        return  questionService.findQuestionById(id);
    }
}
