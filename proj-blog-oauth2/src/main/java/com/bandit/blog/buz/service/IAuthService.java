package com.bandit.blog.buz.service;

import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.bandit.blog.util.base.Result;

public interface IAuthService {
    /**
     * 通过刷新令牌获取新的认证信息
     *
     * @param header 请求头
     * @param refreshToken 刷新令牌
     * @return 结果
     */
    Result refreshToken(String header, String refreshToken) throws HttpProcessException;
}
