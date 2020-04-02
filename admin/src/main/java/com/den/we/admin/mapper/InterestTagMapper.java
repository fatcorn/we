package com.den.we.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.den.we.entity.InterestTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  InterestTagMapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-12
 */

public interface InterestTagMapper extends BaseMapper<InterestTag> {

    /**
     * 分页查询
     * @param page page
     * @return IPage<InterestTag>
     */
    IPage<InterestTag> getTagsByPage(@Param("page") Page page);

    /**
     * 查询父类名称
     * @param superiorId
     * @return
     */
    @Select("select tag_name from interest_tag where superior_id = #{superiorId} limit 1")
    String findNameBySuperiorId(Long superiorId);


}
