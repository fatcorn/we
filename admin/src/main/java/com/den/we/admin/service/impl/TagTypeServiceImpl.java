package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.admin.mapper.TagTypeMapper;
import com.den.we.admin.service.ITagTypeService;
import com.den.we.admin.vo.TagTypeNameVo;
import com.den.we.entity.TagType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fatKarin
 * @since 2020-03-18
 */
@Service
public class TagTypeServiceImpl extends ServiceImpl<TagTypeMapper, TagType> implements ITagTypeService {

    @Override
    public List<TagTypeNameVo> findNameIdMap() {
        return this.baseMapper.findNameIdMap();
    }
}
