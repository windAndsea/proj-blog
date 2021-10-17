package com.bandit.blog.question.mapper;

import com.bandit.blog.entities.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-16
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 通过标签ID分页查询问题信息
     *
     * @param page    分页配置
     * @param labelId 标签ID
     * @return 结果
     */
    IPage<Question> queryListByLabelId(IPage<Question> page, @Param("labelId") String labelId);

    /**
     * 根据问题ID查询问题信息及其所属的标签ID集合
     *
     * @param id 问题ID
     * @return 结果
     */
    Question findQuestionAndLabelIdsById(String id);

    /**
     * 通过问题ID删除问题标签关系表数据
     *
     * @param questionId 问题ID
     * @return 结果
     */
    boolean deleteQuestionLabelByQuestionId(@Param("questionId") String questionId);

    /**
     * 新增问题标签关系数据
     *
     * @param questionId 问题ID
     * @param labelIds 标签集合
     * @return 结果
     */
    boolean insertQuestionLabel(@Param("questionId") String questionId, @Param("labelIds") List<String> labelIds);
}
