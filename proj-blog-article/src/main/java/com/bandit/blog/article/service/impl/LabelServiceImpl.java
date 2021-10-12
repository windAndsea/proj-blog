package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.mapper.LabelMapper;
import com.bandit.blog.article.req.LabelReq;
import com.bandit.blog.article.service.ILabelService;
import com.bandit.blog.entities.Label;
import com.bandit.blog.util.base.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-11
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Override
    public Result queryPage(LabelReq labelReq) {
        IPage<Label> data = baseMapper.queryPage(labelReq.getPage(), labelReq);
        return Result.ok(data);
    }

    @Override
    public boolean updateById(Label entity) {
        entity.setUpdateDate(new Date());
        return super.updateById(entity);
    }
}
