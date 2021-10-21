package com.bandit.blog.article.feign;

import com.bandit.blog.article.service.IArticleService;
import com.bandit.blog.article.service.ILabelService;
import com.bandit.blog.entities.Label;
import com.bandit.blog.feign.IFeignArticleController;
import com.bandit.blog.feign.req.UserInfoReq;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "文章微服务Feign接口")
@RestController
public class FeignArticleController implements IFeignArticleController {
    @Autowired
    private ILabelService labelService;

    @Autowired
    private IArticleService articleService;

    @Override
    public List<Label> queryLabelByLabelIds(List<String> labelIds) {
        return labelService.listByIds(labelIds);
    }

    @Override
    public boolean updateUserInfo(UserInfoReq userInfoReq) {
        return articleService.updateUserInfo(userInfoReq);
    }
}
