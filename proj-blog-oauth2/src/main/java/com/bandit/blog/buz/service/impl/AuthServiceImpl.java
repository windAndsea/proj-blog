package com.bandit.blog.buz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.bandit.blog.auth.constants.OAuthConstants;
import com.bandit.blog.buz.service.IAuthService;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ResultEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Result refreshToken(String header, String refreshToken) throws HttpProcessException {
        ServiceInstance serviceInstance = loadBalancerClient.choose("auth-server");
        if (serviceInstance == null) {
            return Result.error("cannot find authorization microservice");
        }

        String refreshTokenUrl = serviceInstance.getUri().toString() + OAuthConstants.ACCESS_TOKEN_URI;

        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        Header[] headers = HttpHeader.custom()
                .contentType(HttpHeader.Headers.APP_FORM_URLENCODED)
                .authorization(header)
                .build();

        HttpConfig httpConfig = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);

        String httpRes = HttpClientUtil.post(httpConfig);

        if (StringUtils.isNotEmpty(JSONObject.parseObject(httpRes).getString("error"))) {
            return Result.build(ResultEnum.TOKEN_INVALID);
        }
        return Result.ok(JSONObject.parseObject(httpRes));
    }
}
