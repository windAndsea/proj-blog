package com.bandit.blog.oauth2;

import com.bandit.blog.oauth2.constants.OAuthConstants;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.ResultEnum;
import com.bandit.blog.util.tools.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 成功处理器，校验客户端信息，生成jwt令牌，响应result数据
 * （需在config上一级目录下，否则将导致enchance空指针）
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
                                        throws IOException, ServletException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        Result result = null;
        try {
            if (header == null || !StringUtils.startsWith(header, OAuthConstants.AUTHORIZATION_PREFIX)) {
                throw new UnsupportedOperationException("request header cannot find valid authorization");
            }

            String[] headers = RequestUtil.extractAndDecodeHeader(header);
            assert headers.length == 2;
            String clientId = headers[0];
            String clientSecret = headers[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnsupportedOperationException("cannot find db config by clientId, clientId: " + clientId);
            }

            if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnsupportedOperationException("invalid clientSecret");
            }

            // 组合对象，获取令牌
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(),
                    "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

            OAuth2AccessToken oAuth2AccessToken =
                    authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

            result = Result.ok(oAuth2AccessToken);
        } catch (Exception e) {
            LOGGER.error("authentication success handler error", e);
            result = Result.build(ResultEnum.AUTH_FAIL.getCode(), e.getMessage());
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
