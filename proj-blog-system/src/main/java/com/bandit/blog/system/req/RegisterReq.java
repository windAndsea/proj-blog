package com.bandit.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("用户注册请求类")
@Accessors(chain = true)
public class RegisterReq implements Serializable {
    private static final long serialVersionUID = 5461787342900105181L;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", required = true)
    private String confirmPassword;
}
