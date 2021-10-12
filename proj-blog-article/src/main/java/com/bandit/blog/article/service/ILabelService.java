package com.bandit.blog.article.service;

import com.bandit.blog.article.req.LabelReq;
import com.bandit.blog.entities.Label;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author bandit
 * @since 2021-10-11
 */
public interface ILabelService extends IService<Label> {
    /**
     * 分页查询标签列表
     * @param labelReq 请求参数
     * @return 查询结果
     */
    Result queryPage(LabelReq labelReq);
}
