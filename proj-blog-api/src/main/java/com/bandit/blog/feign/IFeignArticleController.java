package com.bandit.blog.feign;

import com.bandit.blog.entities.Label;
import com.bandit.blog.feign.req.UserInfoReq;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "article-server", path = "/article")  // 目标微服务
public interface IFeignArticleController {

    @ApiImplicitParam(allowMultiple = true, dataType = "String", name = "labelIds", value = "标签ID集合", required = true)
    @ApiOperation("feign接口-根据标签ID集合查询标签列表")
    @GetMapping("/api/feign/label/list/{labelIds}")
    List<Label> queryLabelByLabelIds(@PathVariable("labelIds") List<String> labelIds);

    @ApiOperation("feign接口-更新文章表和评论表中的用户信息")
    @PostMapping("/feign/article/user/modify")
    boolean updateUserInfo(@RequestBody UserInfoReq userInfoReq);
}
