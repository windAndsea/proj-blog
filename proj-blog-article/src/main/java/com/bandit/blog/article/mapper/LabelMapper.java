package com.bandit.blog.article.mapper;

import com.bandit.blog.article.req.LabelReq;
import com.bandit.blog.entities.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author bandit
 * @since 2021-10-11
 */
public interface LabelMapper extends BaseMapper<Label> {
    /**
     * 分页查询标签信息
     *
     * @param labelReq 请求参数
     * @return 标签信息
     */
    IPage<Label> queryPage(IPage<Label> page, @Param("labelReq") LabelReq labelReq);
}
