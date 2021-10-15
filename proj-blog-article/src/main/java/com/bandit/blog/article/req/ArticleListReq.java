package com.bandit.blog.article.req;

import com.bandit.blog.entities.Article;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "ArticleListReq对象", description = "文章列表查询条件")
@Data
@Accessors(chain = true)
public class ArticleListReq extends BaseRequest<Article> {
    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    @ApiModelProperty(value = "标签ID")
    private String labelId;
}
