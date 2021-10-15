package com.bandit.blog.article.req;

import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "ArticleUserReq对象", description = "获取指定用户文章的查询条件")
@Data
@Accessors(chain = true)
public class ArticleUserReq extends BaseRequest<Article> {
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "是否公开（0：不公开，1：公开）")
    private Integer isPublic;
}
