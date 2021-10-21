package com.bandit.blog.feign;

import com.bandit.blog.feign.req.UserInfoReq;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "question-server", path = "/question")  // 目标微服务
public interface IFeignQuestionController {
    @ApiOperation("feign接口-更新问题表和回答表中的用户信息")
    @PostMapping("/feign/question/user/modify")
    boolean updateUserInfo(@RequestBody UserInfoReq userInfoReq);
}
