package com.bandit.blog.util.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 请求参数基础类，带分页参数
 *
 * @param <T> 实体类
 */
@ApiModel(value = "请求参数基础类，带分页参数")
@Data
@Accessors
public class BaseRequest<T> implements Serializable {
    @ApiModelProperty(value = "页码", required = true)
    private Long current;

    @ApiModelProperty(value = "每页显示记录数", required = true)
    private Long size;

    /**
     * 封装分页对象
     *
     * @return 分页对象
     */
    @ApiModelProperty(hidden = true)
    public IPage<T> getPage() {
        return new Page<T>().setCurrent(this.current).setSize(this.size);
    }
}
