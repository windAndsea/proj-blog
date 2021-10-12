package com.bandit.blog.article.service;

import com.bandit.blog.article.req.CategoryReq;
import com.bandit.blog.entities.Category;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文章分类表-服务类(业务层)
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 分页条件查询分类信息
     *
     * @param categoryReq 查询条件
     * @return 分类信息
     */
    Result queryPage(CategoryReq categoryReq);

    /**
     * 查询所有正常状态的分类信息
     *
     * @return 分类信息
     */
    Result findAllNormal();

    /**
     * 查询所有正常分类及下的标签集合
     *
     * @return 结果集
     */
    Result findAllNormalCategoryAndLabel();
}
