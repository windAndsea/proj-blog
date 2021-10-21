package com.bandit.blog.system.req;

import com.bandit.blog.entities.SysUser;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "SysUserReq对象", description = "用户查询条件")
public class SysUserReq extends BaseRequest<SysUser> {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String mobile;
}
