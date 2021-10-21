package com.bandit.blog.question.feign;

import com.bandit.blog.feign.IFeignQuestionController;
import com.bandit.blog.feign.req.UserInfoReq;
import com.bandit.blog.question.service.IQuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "问答微服务Feign接口")
@RestController
public class FeignQuestionController implements IFeignQuestionController {
    @Autowired
    private IQuestionService questionService;

    @Override
    public boolean updateUserInfo(UserInfoReq userInfoReq) {
        return questionService.updateUserInfo(userInfoReq);
    }
}
