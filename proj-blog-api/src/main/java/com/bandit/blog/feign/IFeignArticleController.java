package com.bandit.blog.feign;

import com.bandit.blog.entities.Label;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/feign")
public interface IFeignArticleController {

    @ApiImplicitParam(allowMultiple = true, dataType = "String", name = "labelIds", value = "标签ID集合", required = true)
    @ApiOperation("feign接口-根据标签ID集合查询标签列表")
    @GetMapping("/label/list/{labelIds}")
    List<Label> queryLabelByLabelIds(@PathVariable("labelIds") List<String> labelIds);
}
