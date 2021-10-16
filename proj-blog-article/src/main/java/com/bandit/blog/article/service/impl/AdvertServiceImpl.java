package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.AdvertMapper;
import com.bandit.blog.article.req.AdvertReq;
import com.bandit.blog.article.service.IAdvertService;
import com.bandit.blog.entities.Advert;
import com.bandit.blog.util.aliyun.AliyunUtil;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.StatusEnum;
import com.bandit.blog.util.properties.BlogProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements IAdvertService {
    @Autowired
    private BlogProperties blogProperties;

    @Override
    public Result queryPage(AdvertReq advertReq) {
        QueryWrapper<Advert> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(advertReq.getTitle())) {
            queryWrapper.like("title", advertReq.getTitle());
        }

        if (advertReq.getStatus() != null) {
            queryWrapper.eq("status", advertReq.getStatus());
        }

        IPage<Advert> advertIPage = baseMapper.selectPage(advertReq.getPage(), queryWrapper);
        return Result.ok(advertIPage);
    }

    @Transactional
    @Override
    public Result deleteAdvertById(String id) {
        // 查询记录
        Advert advert = baseMapper.selectById(id);
        if (advert == null) {
            Result.error("the advert is not exist.");
        }

        baseMapper.deleteById(id);
        // 图片记录存放在OSS上，需要删除
        String imageUrl = advert.getImageUrl();

        AliyunUtil.delete(imageUrl, blogProperties.getAliyun());
        return Result.ok();
    }

    @Override
    public Result modifyAdvert(Advert advert) {
        advert.setUpdateDate(new Date());
        baseMapper.updateById(advert);
        return Result.ok();
    }

    @Override
    public Result findByPosition(int position) {
        QueryWrapper<Advert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusEnum.NORMAL.getCode());
        queryWrapper.eq("position", position);
        queryWrapper.orderByAsc("sort");
        List<Advert> advertList = baseMapper.selectList(queryWrapper);
        return Result.ok(advertList);
    }
}
