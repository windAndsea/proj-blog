package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.ArticleMapper;
import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.article.service.IArticleService;
import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public Result queryPage(ArticleReq articleReq) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(articleReq.getTitle())) {
            queryWrapper.like("title", articleReq.getTitle());
        }

        if (articleReq.getStatus() != null) {
            queryWrapper.eq("status", articleReq.getStatus());
        }

        queryWrapper.orderByDesc("update_date");
        IPage<Article> data = baseMapper.selectPage(articleReq.getPage(), queryWrapper);
        return Result.ok(data);
    }
}
