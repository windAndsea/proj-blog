package com.bandit.blog.question.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.bandit.blog.entities.Question;
import com.bandit.blog.question.mapper.QuestionMapper;
import com.bandit.blog.question.service.IQuestionService;
import com.bandit.blog.util.base.BaseRequest;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Override
    public Result findHotQuestion(BaseRequest<Question> request) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();

        // 查询记录状态为1 or 2的记录
        queryWrapper.in("status", Arrays.asList(1, 2));
        queryWrapper.orderByDesc("reply");
        IPage<Question> questionIPage = baseMapper.selectPage(request.getPage(), queryWrapper);
        return Result.ok(questionIPage);
    }

    @Override
    public Result findLatestQuestion(BaseRequest<Question> request) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();

        // 查询记录状态为1 or 2的记录
        queryWrapper.in("status", Arrays.asList(1, 2));
        queryWrapper.orderByDesc("update_date");
        IPage<Question> questionIPage = baseMapper.selectPage(request.getPage(), queryWrapper);
        return Result.ok(questionIPage);
    }

    @Override
    public Result findWaitReplyQuestion(BaseRequest<Question> request) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("reply", 0);
        // 查询记录状态为1 or 2的记录
        queryWrapper.in("status", Arrays.asList(1, 2));
        queryWrapper.orderByDesc("create_date");
        IPage<Question> questionIPage = baseMapper.selectPage(request.getPage(), queryWrapper);
        return Result.ok(questionIPage);
    }

    @Override
    public Result findQuestionByLabelId(BaseRequest<Question> request, String labelId) {
        if (StringUtils.isEmpty(labelId)) {
            return Result.error("label id can not be empty!");
        }

        return Result.ok(baseMapper.queryListByLabelId(request.getPage(), labelId));
    }

    @Override
    public Result findQuestionById(String id) {
        // 1，查询问题信息
        Question question = baseMapper.findQuestionAndLabelIdsById(id);
        if (question == null) {
            return Result.error("cannot find question info");
        }

        if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
            // TODO 2，通过feign远程调用article接口获取标签信息
        }

        return Result.ok(question);
    }
}
