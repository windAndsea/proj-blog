package com.bandit.blog.article.mapper;

import com.bandit.blog.entities.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-15
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 通过文章ID查询评论
     *
     * @param articleId 文章ID
     * @return 结果
     */
    List<Comment> findByArticleId(@Param("articleId") String articleId);

}
