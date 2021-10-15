package com.bandit.blog.article.service;

import com.bandit.blog.entities.Comment;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-15
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 查询文章评论
     *
     * @param articleId 文章ID
     * @return 评论
     */
    Result findByArticleId(String articleId);

    /**
     * 通过评论ID删除所属评论
     *
     * @param id 评论ID
     * @return 结果
     */
    Result deleteById(String id);
}
