package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.enums.StatusEnum;
import com.bandit.blog.article.mapper.CategoryMapper;
import com.bandit.blog.article.req.CategoryReq;
import com.bandit.blog.article.service.ICategoryService;
import com.bandit.blog.entities.Category;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public Result queryPage(CategoryReq categoryReq) {
        // 查询条件对象
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(categoryReq.getName())) {
            queryWrapper.like("name", categoryReq.getName());
        }

        if (categoryReq.getStatus() != null) {
            queryWrapper.eq("status", categoryReq.getStatus());
        }

        queryWrapper.orderByDesc("status").orderByAsc("sort");
        IPage<Category> data = baseMapper.selectPage(categoryReq.getPage(), queryWrapper);
        return Result.ok(data);
    }

    @Override
    public Result findAllNormal() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.NORMAL.getCode());
        List<Category> categories = baseMapper.selectList(queryWrapper);
        return Result.ok(categories);
    }

    @Override
    public Result findAllNormalCategoryAndLabel() {
        List<Category> categories = baseMapper.findAllNormalCategoryAndLabel();
        return Result.ok(categories);
    }

    @Override
    public boolean updateById(Category entity) {
        // 设置更新时间
        entity.setUpdateDate(new Date());
        return super.updateById(entity);
    }

}
