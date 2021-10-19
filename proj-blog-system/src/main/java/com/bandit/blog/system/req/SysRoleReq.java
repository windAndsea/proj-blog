package com.bandit.blog.system.req;

import com.bandit.blog.entities.SysRole;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "SysRoleReq对象", description = "角色查询条件")
public class SysRoleReq extends BaseRequest<SysRole> {

    @ApiModelProperty(value = "角色名称")
    private String name;
}
