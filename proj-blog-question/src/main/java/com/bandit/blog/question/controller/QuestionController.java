package com.bandit.blog.question.controller;

import com.bandit.blog.entities.Question;
import com.bandit.blog.question.req.QuestionUserReq;
import com.bandit.blog.question.service.IQuestionService;
import com.bandit.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(tags = "问答管理接口")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @PostMapping("/add")
    @ApiOperation("新增问题信息")
    public Result addQuestion(@RequestBody Question question) {
        return questionService.saveOrUpdateQuestion(question);
    }

    @PostMapping("/modify")
    @ApiOperation("修改问题信息")
    public Result updateQuestion(@RequestBody Question question) {
        return questionService.saveOrUpdateQuestion(question);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除问题信息")
    @ApiImplicitParam(name = "id", value = "问题ID", required = true)
    public Result deleteQuestion(@PathVariable("id") String id) {
        return questionService.deleteQuestionById(id);
    }

    @ApiOperation("更新点赞数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "问题ID", required = true),
            @ApiImplicitParam(name = "count", value = "点赞数（只能是1或-1）", required = true)
    })
    @PostMapping("/thumb/{id}/{count}")
    public Result updateThumbHup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return questionService.updateThumbHup(id, count);
    }

    @ApiOperation("查询用户问题信息")
    @PostMapping("/user")
    public Result queryUserQuestion(@RequestBody QuestionUserReq questionUserReq) {
        return questionService.findQuestionByCon(questionUserReq);
    }
}
