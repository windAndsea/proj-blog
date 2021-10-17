package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.CommentMapper;
import com.bandit.blog.article.service.ICommentService;
import com.bandit.blog.entities.Comment;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public Result findByArticleId(String articleId) {
        if (StringUtils.isEmpty(articleId)) {
            return Result.error("articleId not be empty");
        }

        List<Comment> comments = baseMapper.findByArticleId(articleId);
        return Result.ok(comments);
    }

    @Override
    public Result deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("comment id not be empty");
        }

        List<String> commentIds = new ArrayList<>();
        commentIds.add(id);
        commentIds.addAll(this.findSubCommentIdsByParent(id));

        baseMapper.deleteBatchIds(commentIds);
        return Result.ok();
    }

    /**
     * 通过父评论ID递归查询子评论（ex:1）
     *
     * @param parentId 父评论ID
     * @return 子评论
     */
    private List<String> findSubCommentIdsByParent(String parentId) {
        List<String> subCommentIds = new ArrayList<>();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);

        List<Comment> comments = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(comments)) {
            return subCommentIds;
        }

        for (Comment comment : comments) {
            String commentId = comment.getId();
            subCommentIds.add(commentId);
            List<String> tempCommentList = findSubCommentIdsByParent(commentId);
            if (CollectionUtils.isNotEmpty(tempCommentList)) {
                subCommentIds.addAll(tempCommentList);
            }

        }
        return subCommentIds;
    }

    /**
     * 通过父评论ID递归查询子评论（ex:2）
     *
     * @param commentIds 待处理的评论ID集合
     * @param parentId 父评论ID
     */
    public void findSubCommentIdsByParent(List<String> commentIds, String parentId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);

        List<Comment> comments = baseMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(comments)) {
            return;
        }

        for (Comment comment : comments) {
            String commentId = comment.getId();
            commentIds.add(commentId);
            this.findSubCommentIdsByParent(commentIds, commentId);
        }
    }
}
