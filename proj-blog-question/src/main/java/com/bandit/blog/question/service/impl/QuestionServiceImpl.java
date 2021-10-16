package com.bandit.blog.question.service.impl;

import com.bandit.blog.entities.Question;
import com.bandit.blog.question.mapper.QuestionMapper;
import com.bandit.blog.question.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
