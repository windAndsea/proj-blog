package com.bandit.blog.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "proj.blog")
public class BlogProperties implements Serializable {
    private static final long serialVersionUID = 2971178244967659697L;

    private AliyunProperties aliyun;
}
