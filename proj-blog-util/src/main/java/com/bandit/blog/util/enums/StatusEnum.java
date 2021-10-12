package com.bandit.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    NORMAL(1, "正常"),
    FORBIDDEN(0, "禁用");

    private Integer code;

    private String desc;
}
