package com.bandit.blog.article.mapper;

import com.bandit.blog.entities.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 文章分类表Mapper接口
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询所有正常分类下的标签
     *
     * @return 分类及标签信息集合
     */
    List<Category> findAllNormalCategoryAndLabel();
}
