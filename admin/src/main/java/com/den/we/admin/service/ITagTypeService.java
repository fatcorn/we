package com.den.we.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.admin.vo.TagTypeNameVo;
import com.den.we.entity.TagType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-18
 */
public interface ITagTypeService extends IService<TagType> {

    /**
     * 查询类型id与类型映射
     * @return  List
     */
    List<TagTypeNameVo> findNameIdMap();

}
