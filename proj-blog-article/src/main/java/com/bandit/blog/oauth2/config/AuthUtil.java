package com.bandit.blog.oauth2.config;

import com.bandit.blog.entities.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class AuthUtil {

    /**
     * 获取用户信息
     *
     * @return
     */
    public static SysUser getUserInfo() {
        // 获取从Security上下文中获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> map = (Map<String, Object>) details.getDecodedDetails();
        Map<String, String> userInfo = (Map<String, String>) map.get("userInfo");
        SysUser user = new SysUser();
        String mobile = userInfo.get("mobile");
        user.setId(userInfo.get("uid"));
        user.setUsername(userInfo.get("username"));
        user.setEmail(userInfo.get("email"));
        user.setNickName(userInfo.get("nickName"));
        user.setImageUrl(userInfo.get("imageUrl"));

        return user;
    }
}
