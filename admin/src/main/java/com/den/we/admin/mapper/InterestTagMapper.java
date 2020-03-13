package com.den.we.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.den.we.entity.InterestTag;
import org.apache.ibatis.annotations.Param;

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

    IPage<InterestTag> getTagsByPage(@Param("page") Page page);
}
