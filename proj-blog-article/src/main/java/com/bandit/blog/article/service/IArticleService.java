package com.bandit.blog.article.service;

import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.Result;
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
}
