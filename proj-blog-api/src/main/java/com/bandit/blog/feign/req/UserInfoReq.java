package com.bandit.blog.feign.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ApiModel("用户信息请求类")
public class UserInfoReq implements Serializable {
    private static final long serialVersionUID = -8233542829580377453L;

    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "头像", required = true)
    private String userImage;
}
