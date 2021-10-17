package com.bandit.blog.question.req;

import com.bandit.blog.entities.Question;
import com.bandit.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "QuestionUserReq", description = "用户问题信息查询条件")
public class QuestionUserReq extends BaseRequest<Question> {
    @ApiModelProperty(value = "用户ID")
    private String userId;
}
