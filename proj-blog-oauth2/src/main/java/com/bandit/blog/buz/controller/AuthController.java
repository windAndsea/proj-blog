package com.bandit.blog.buz.controller;

import com.bandit.blog.auth.constants.OAuthConstants;
import com.bandit.blog.buz.service.IAuthService;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.tools.RequestUtil;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = "认证接口")
@RestController
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthService authService;

    @PostMapping("/user/refresh/token")
    public Result refreshToken(HttpServletRequest httpServletRequest) {
        try {
            String refreshToken = httpServletRequest.getParameter("refreshToken");

            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "refresh token cannot be empty");

            String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
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

            return authService.refreshToken(header, refreshToken);
        } catch (Exception e) {
            LOGGER.error("refresh token error", e);
            return Result.error("refresh token error, " + e.getMessage());
        }
    }
}
