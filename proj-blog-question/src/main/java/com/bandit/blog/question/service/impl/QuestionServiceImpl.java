package com.bandit.blog.question.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.bandit.blog.entities.Article;
import com.bandit.blog.entities.Question;
import com.bandit.blog.question.mapper.QuestionMapper;
import com.bandit.blog.question.req.QuestionUserReq;
import com.bandit.blog.question.service.IQuestionService;
import com.bandit.blog.util.base.BaseRequest;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ArticleStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

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

    @Override
    public Result updateViewCount(String id) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            return Result.error("invalid operation");
        }

        Question question = baseMapper.selectById(id);
        if (question == null) {
            return Result.error("question is not exist.");
        }
        question.setViewCount(question.getViewCount() + 1);
        baseMapper.updateById(question);
        return Result.ok();
    }

    @Transactional
    @Override
    public Result saveOrUpdateQuestion(Question question) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(question.getId())) {
            // 更新操作
            baseMapper.deleteQuestionLabelByQuestionId(question.getId());
            question.setUpdateDate(new Date());
        }

        super.saveOrUpdate(question);

        // 判断标签是否存在
        if (CollectionUtils.isNotEmpty(question.getLabelIds())) {
            baseMapper.insertQuestionLabel(question.getId(), question.getLabelIds());
        }
        return Result.ok(question.getId());
    }

    @Override
    public Result updateStatus(String id, Integer status) {
        Question question = baseMapper.selectById(id);
        if (question == null) {
            Result.error("the question is not exist");
        }
        question.setStatus(status);
        question.setUpdateDate(new Date());
        baseMapper.updateById(question);
        return Result.ok();
    }

    @Override
    public Result deleteQuestionById(String id) {
        this.updateStatus(id, 0);
        return Result.ok();
    }

    @Override
    public Result updateThumbHup(String id, int count) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            return Result.error("invalid operation");
        }

        if (count != -1 && count != 1) {
            return Result.error("invalid operation");
        }

        Question question = baseMapper.selectById(id);
        if (question == null) {
            return Result.error("question is not exist.");
        }
        int thumbHup = question.getThumhup();

        int calThumbHup = thumbHup + count;
        if (calThumbHup < 0) {
            return Result.error("invalid operation");
        }

        question.setThumhup(calThumbHup);

        baseMapper.updateById(question);
        return Result.ok();
    }

    @Override
    public Result findQuestionByCon(QuestionUserReq req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("userId cannot be empty");
        }

        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        // 排除已删除的问题
        queryWrapper.in("status", Arrays.asList(1, 2));

        queryWrapper.eq("user_id", req.getUserId());
        queryWrapper.orderByDesc("update_date");
        return Result.ok(baseMapper.selectPage(req.getPage(), queryWrapper));
    }
}
