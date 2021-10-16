package com.bandit.blog.article.controller;

import com.bandit.blog.util.aliyun.AliyunUtil;
import com.bandit.blog.util.base.Result;
import com.bandit.blog.util.enums.PlatformEnum;
import com.bandit.blog.util.properties.BlogProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件管理接口")
@RequestMapping("/file")
@RestController
public class FileController {
    @Autowired
    private BlogProperties blogProperties;

    @ApiOperation("上传文件到阿里云OSS服务器")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        return AliyunUtil.uploadFileToOss(PlatformEnum.ARTICLE, file, blogProperties.getAliyun());
    }

    @ApiOperation("删除阿里云OSS服务器上指定文件")
    @PostMapping("/delete")
    @ApiImplicitParam(name = "fileUrl", value = "要删除的文件URL", required = true)
    public Result delete(@RequestParam(value = "fileUrl", required = true) String fileUrl) {
        return AliyunUtil.delete(fileUrl, blogProperties.getAliyun());
    }
}
