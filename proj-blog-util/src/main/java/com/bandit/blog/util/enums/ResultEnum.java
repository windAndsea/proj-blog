package com.bandit.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS("000000", "success"),

    /** ---------- 0***** - 8*****为业务级异常拦截 ----------- **/

    /** ---------- 9*****为系统级校验异常拦截 ----------- **/
    UNAUTHENTICATED("900001", "Please pass identity authentication first!"),

    AUTH_FAIL("900002", "identity authentication fail"),

    TOKEN_INVALID("900003", "token invalid"),

    TOKEN_TIMEOUT("900004", "token timeout"),

    HEADER_ERROR("900005", "request header error"),

    MENU_UN_AUTH("900006", "not owner the menu permission"),

    AUTH_USERNAME_EMPTY("900007", "username cannot be empty!"),

    AUTH_PASSWORD_EMPTY("900008", "password cannot be empty!"),

    ERROR("999999", "system error");

    private String code;

    private String desc;
}
