package com.bandit.blog.util.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 请求处理器
 */
public class RequestUtil {
    public static String[] extractAndDecodeHeader(String header) throws IOException {
        byte[] base64Token = header.trim().substring(6).getBytes(StandardCharsets.UTF_8);

        byte[] decoded;

        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (Exception e) {
            throw new RuntimeException("request header parse error." + header);
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new RuntimeException("request header is invalid: " + token);
        }

        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
