package com.bandit.blog.article.req;

import com.bandit.blog.entities.Label;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "LabelReq对象", description = "标签查询条件")
@Data
@Accessors
public class LabelReq extends BaseRequest<Label> {
    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "分类ID")
    private String categoryId;
}
