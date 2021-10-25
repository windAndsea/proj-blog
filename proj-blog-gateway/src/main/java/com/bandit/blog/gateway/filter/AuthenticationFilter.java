package com.bandit.blog.gateway.filter;

import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
    private static final String[] WHITE_URI_PATTERN = {"/api/"};

    /**
     * 验证请求头是否带Authorization
     *
     * @param exchange HTTP请求-响应交互的契约
     * @param chain 过滤链
     * @return 结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().pathWithinApplication().value();
        if (StringUtils.indexOfAny(path, WHITE_URI_PATTERN) != -1) {
            // 白名单uri，直接放行
            return chain.filter(exchange);
        }

        // 非白名单uri，需进行认证
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        ServerHttpResponse response = exchange.getResponse();
        if (StringUtils.isEmpty(authorization)) {
            JSONObject message = new JSONObject();
            message.put("code", "1401");
            message.put("message", "Authorization cannot be empty");

            // 转换响应消息内容对象为字节
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);

            // 设置响应对象状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE,  "application/json;charset=UTF-8");
            // 返回响应对象
            return response.writeWith( Mono.just(buffer) );

        }

        // 如果请求头不为空，则验证通过，放行此过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
