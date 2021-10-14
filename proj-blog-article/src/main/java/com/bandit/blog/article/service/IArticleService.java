package com.bandit.blog.article.service;

import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.article.req.ArticleUserReq;
import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ArticleStatusEnum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-12
 */
public interface IArticleService extends IService<Article> {
    /**
     * 条件分页查询文章列表
     *
     * @param articleReq 请求参数
     * @return 结果
     */
    Result queryPage(ArticleReq articleReq);

    /**
     * 通过文章id查询文章详情及标签
     *
     * @param id 文章id
     * @return 结果
     */
    Result findArticleAndLabelById(String id);

    /**
     * 保存或更新文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    Result saveOrUpdateArticle(Article article);

    /**
     * 更新文章状态
     *
     * @param id                文章id
     * @param articleStatusEnum 文章状态
     * @return 结果
     */
    Result updateStatus(String id, ArticleStatusEnum articleStatusEnum);

    /**
     * 根据用户ID查询文章
     *
     * @param articleUserReq 请求参数
     * @return 结果
     */
    Result findListByUserId(ArticleUserReq articleUserReq);

    /**
     * 根据文章ID更新点赞数
     *
     * @param id 文章ID
     * @param count 1：点赞接收， -1取消点赞
     * @return 结果
     */
    Result updateThumbHup(String id, int count);


}
