package com.den.we.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.admin.vo.TagTypeNameVo;
import com.den.we.entity.TagType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  TagTypeMapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-18
 */

public interface TagTypeMapper extends BaseMapper<TagType> {

    /**
     * 查询类型id与类型映射
     * @return  List
     */
    @Select("select id typeId,name typeName from tag_type")
    List<TagTypeNameVo> findNameIdMap();
}
