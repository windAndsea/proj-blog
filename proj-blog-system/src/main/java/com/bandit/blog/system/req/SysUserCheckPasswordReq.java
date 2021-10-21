package com.bandit.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "SysUserCheckPasswordReq对象", description = "用户密码校验请求参数")
public class SysUserCheckPasswordReq implements Serializable {
    private static final long serialVersionUID = -621748780050188359L;

    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "原密码", required = true)
    private String oldPassword;
}
