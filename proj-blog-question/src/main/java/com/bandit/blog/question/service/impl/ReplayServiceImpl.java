package com.bandit.blog.question.service.impl;

import com.bandit.blog.entities.Replay;
import com.bandit.blog.question.mapper.ReplayMapper;
import com.bandit.blog.question.service.IReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Service
public class ReplayServiceImpl extends ServiceImpl<ReplayMapper, Replay> implements IReplayService {

}
