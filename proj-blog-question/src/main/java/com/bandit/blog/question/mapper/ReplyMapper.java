package com.bandit.blog.question.mapper;

import com.bandit.blog.entities.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回答信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 通过问题ID查询所有回复数据
     *
     * @param questionId 问题ID
     * @return 结果
     */
    List<Reply> findByQuestionId(@Param("questionId") String questionId);
}
