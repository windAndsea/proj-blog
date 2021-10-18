package com.bandit.blog.question.service.impl;

import com.bandit.blog.entities.Comment;
import com.bandit.blog.entities.Question;
import com.bandit.blog.entities.Reply;
import com.bandit.blog.question.mapper.QuestionMapper;
import com.bandit.blog.question.mapper.ReplyMapper;
import com.bandit.blog.question.service.IReplyService;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Result findByQuestionId(String questionId) {
        if (StringUtils.isEmpty(questionId)) {
            return Result.error("questionId cannot be empty");
        }
        return Result.ok(baseMapper.findByQuestionId(questionId));
    }

    @Transactional
    @Override
    public Result deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("reply id not be empty");
        }

        List<String> replyIds = new ArrayList<>();
        replyIds.add(id);
        this.findSubRelyIdsByParent(replyIds, id);

        // 通过回复ID查询问题
        Reply reply = baseMapper.selectById(id);

        int size = baseMapper.deleteBatchIds(replyIds);

        if (size > 0) {
            Question question = questionMapper.selectById(reply.getQuestionId());
            question.setReply(question.getReply() - size);
            questionMapper.updateById(question);
        }

        return Result.ok();
    }

    private void findSubRelyIdsByParent(List<String> replyIds, String parentId) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);

        List<Reply> replies = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(replies)) {
            return;
        }

        for (Reply reply : replies) {
            String replyId = reply.getId();
            replyIds.add(replyId);
            this.findSubRelyIdsByParent(replyIds, replyId);
        }
    }

    @Transactional
    @Override
    public Result addReply(Reply reply) {
        boolean saveRes = this.save(reply);
        if (saveRes) {
            // 更新问题表回复数
            Question question = questionMapper.selectById(reply.getQuestionId());
            question.setReply(question.getReply() + 1);
            questionMapper.updateById(question);
        }
        return Result.ok();
    }
}
