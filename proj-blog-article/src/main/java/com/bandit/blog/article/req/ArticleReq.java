package com.bandit.blog.article.req;

import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
@ApiModel(value = "ArticleReq对象", description = "文章查询条件")
public class ArticleReq extends BaseRequest<Article> {
    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "状态，0: 已删除, 1:未审核，2:审核通过，3：审核未通过")
    private Integer status;
}
