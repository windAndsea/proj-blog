package com.bandit.blog.question.service;

import com.bandit.blog.entities.Reply;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回答信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
public interface IReplyService extends IService<Reply> {
    /**
     * 通过问题ID查询所有回复数据
     *
     * @param questionId 问题ID
     * @return 结果
     */
    Result findByQuestionId(String questionId);

    /**
     * 通过回复ID递归删除回复信息
     *
     * @param id 回复ID
     * @return 结果
     */
    Result deleteById(String id);

    /**
     * 新增回复
     *
     * @param reply 回复对象
     * @return 结果
     */
    Result addReply(Reply reply);
}
