package com.bandit.blog.gateway.filter;

import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@Component
public class AccessTokenFilter implements GlobalFilter, Ordered {
    private final Logger LOGGER = LoggerFactory.getLogger(AccessTokenFilter.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 校验Authorization有效性
     *
     * @param exchange HTTP请求-响应交互的契约
     * @param chain    过滤链
     * @return 结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // token组成：header.payload.signature
        String token = StringUtils.substringAfter(authorization, "Bearer ");

        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }

        String message = null;
        try {
            JWSObject parse = JWSObject.parse(token);
            JSONObject jsonObject = parse.getPayload().toJSONObject();

            String jti = jsonObject.get("jti").toString();
            Object cacheToken = redisTemplate.opsForValue().get(jti);

            if (cacheToken == null) {
                LOGGER.warn("token timeout");
                message = "token timeout";
            }
        } catch (ParseException e) {
            LOGGER.error("parse token error", e);
            message = "invalid token";
        }

        if (message == null) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();

        JSONObject res = new JSONObject();
        res.put("code", "1401");
        res.put("message", message);
        // 转换响应消息内容对象为字节
        byte[] bits = res.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);

        // 设置响应对象状态码 401
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        // 返回响应对象
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
