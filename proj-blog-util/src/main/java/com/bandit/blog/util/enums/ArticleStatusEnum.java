package com.bandit.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章状态枚举
 */
@AllArgsConstructor
@Getter
public enum ArticleStatusEnum {
    DELETE(0, "已删除"),

    WAIT(1, "待审核"),

    PASS(2, "审核通过"),

    REJECT(3, "审核拒绝");

    private Integer code;

    private String desc;
}
