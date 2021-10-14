package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.ArticleMapper;
import com.bandit.blog.article.req.ArticleReq;
import com.bandit.blog.article.req.ArticleUserReq;
import com.bandit.blog.article.service.IArticleService;
import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ArticleStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Override
    public Result findArticleAndLabelById(String id) {
        return Result.ok(baseMapper.findArticleAndLabelById(id));
    }

    @Transactional
    @Override
    public Result saveOrUpdateArticle(Article article) {
        if (StringUtils.isNotEmpty(article.getId())) {
            // 更新操作
            baseMapper.deleteArticleLabel(article.getId());
            article.setUpdateDate(new Date());
        }

        if (article.getIspublic() == 0) {
            // 文章不公开, 状态设置为审核通过
            article.setStatus(ArticleStatusEnum.PASS.getCode());
        } else {
            // 文章公开, 状态设置为待审核
            article.setStatus(ArticleStatusEnum.WAIT.getCode());
        }

        super.saveOrUpdate(article);

        // 判断标签是否存在
        if (CollectionUtils.isNotEmpty(article.getLabelIds())) {
            baseMapper.saveArticleLabel(article.getId(), article.getLabelIds());
        }
        return Result.ok(article.getId());
    }

    @Override
    public Result updateStatus(String id, ArticleStatusEnum articleStatusEnum) {
        Article article = baseMapper.selectById(id);
        article.setStatus(articleStatusEnum.getCode());
        article.setUpdateDate(new Date());
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByUserId(ArticleUserReq articleUserReq) {
        if (StringUtils.isEmpty(articleUserReq.getUserId())) {
            return Result.error("invalid user");
        }
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", articleUserReq.getUserId());

        if (articleUserReq.getIsPublic() != null) {
            queryWrapper.eq("ispublic", articleUserReq.getIsPublic());
        }

        IPage<Article> data = baseMapper.selectPage(articleUserReq.getPage(), queryWrapper);
        return Result.ok(data);
    }
}
