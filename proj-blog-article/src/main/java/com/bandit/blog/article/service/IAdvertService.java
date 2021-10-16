package com.bandit.blog.article.service;

import com.bandit.blog.article.req.AdvertReq;
import com.bandit.blog.entities.Advert;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告信息表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
public interface IAdvertService extends IService<Advert> {

    /**
     * 分页查询广告列表
     *
     * @param advertReq 请求参数
     * @return 结果
     */
    Result queryPage(AdvertReq advertReq);
}
