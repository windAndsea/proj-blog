package com.bandit.blog.util.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * 阿里云OSS对象存储配置
 */
@Data
public class AliyunProperties implements Serializable {
    private static final long serialVersionUID = -210873326713723535L;

    /**
     * 地域节点
     */
    private String endpoint;

    /**
     * ak id
     */
    private String accessKeyId;

    /**
     * ak secret
     */
    private String accessKeySecret;

    /**
     * bucket 名称
     */
    private String bucketName;

    /**
     * bucket 域名
     */
    private String bucketDomain;
}
