package com.den.we.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.entity.InterestTag;
import com.den.we.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */

public interface InterestTagMapper extends BaseMapper<InterestTag> {

    @Select("select * from interest_tag where tag_name=#{tagName} limit 1")
    InterestTag findByTagName(@Param("tagName") String tagName);

}
