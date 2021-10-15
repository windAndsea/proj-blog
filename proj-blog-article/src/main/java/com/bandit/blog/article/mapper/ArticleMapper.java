package com.bandit.blog.article.mapper;

import com.bandit.blog.article.req.ArticleListReq;
import com.bandit.blog.entities.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-12
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 通过文章id查询文章详情及标签
     *
     * @param id 文章id
     * @return 结果
     */
    Article findArticleAndLabelById(String id);

    /**
     * 通过文章ID删除文章标签中间表记录
     *
     * @param articleId 文章ID
     * @return 结果
     */
    boolean deleteArticleLabel(@Param("articleId") String articleId);

    /**
     * 保存文章标签中间表记录
     *
     * @param articleId 文章ID
     * @param labelIds  标签ID集合
     * @return 结果
     */
    boolean saveArticleLabel(@Param("articleId") String articleId, @Param("labelIds") List<String> labelIds);

    /**
     * 通过分类Id和标签ID查询公开且审核通过的文章信息
     *
     * @param page 分页信息
     * @param articleListReq 请求参数
     * @return 文章信息
     */
    IPage<Article> findListByCategoryIdAndLabelId(IPage<Article> page, @Param("articleListReq") ArticleListReq articleListReq);
}
