package com.bandit.blog.article.req;

import com.bandit.blog.entities.Advert;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "AdvertReq对象", description = "广告查询条件")
public class AdvertReq extends BaseRequest<Advert> {
    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;
}
