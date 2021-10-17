package com.bandit.blog.question.service;

import com.bandit.blog.entities.Question;
import com.bandit.blog.util.base.BaseRequest;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ArticleStatusEnum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 热门问答查询
     *
     * @param request 请求参数
     * @return 结果
     */
    Result findHotQuestion(BaseRequest<Question> request);

    /**
     * 最新问答查询
     *
     * @param request 请求参数
     * @return 结果
     */
    Result findLatestQuestion(BaseRequest<Question> request);

    /**
     * 查询等待回答的问题
     *
     * @param request 请求参数
     * @return 结果
     */
    Result findWaitReplyQuestion(BaseRequest<Question> request);

    /**
     * 通过标签ID查询问题
     *
     * @param request 请求参数
     * @param labelId 标签ID
     * @return 结果
     */
    Result findQuestionByLabelId(BaseRequest<Question> request, String labelId);

    /**
     * 通过问题ID查询问题信息
     *
     * @param id 问题ID
     * @return 结果
     */
    Result findQuestionById(String id);

    /**
     * 更新问题浏览数
     *
     * @param id 问题ID
     * @return 结果
     */
    Result updateViewCount(String id);

    /**
     * 新增或修改问题信息
     *
     * @param question 问题对象
     * @return 结果
     */
    Result saveOrUpdateQuestion(Question question);

    /**
     * 通过问题ID删除问题
     *
     * @param id 问题ID
     * @return 结果
     */
    Result deleteQuestionById(String id);

    /**
     * 更新问题状态
     *
     * @param id                问题id
     * @param status 状态
     * @return 结果
     */
    Result updateStatus(String id, Integer status);

    /**
     * 根据问题ID更新点赞数
     *
     * @param id    问题ID
     * @param count 1：点赞接收， -1取消点赞
     * @return 结果
     */
    Result updateThumbHup(String id, int count);
}
