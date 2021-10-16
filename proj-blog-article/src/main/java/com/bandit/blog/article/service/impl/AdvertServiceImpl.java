package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.AdvertMapper;
import com.bandit.blog.article.req.AdvertReq;
import com.bandit.blog.article.service.IAdvertService;
import com.bandit.blog.entities.Advert;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
}
