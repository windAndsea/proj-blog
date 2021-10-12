package com.bandit.blog.article.service.impl;

import com.bandit.blog.article.entities.Label;
import com.bandit.blog.article.mapper.LabelMapper;
import com.bandit.blog.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
