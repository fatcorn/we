package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.TagSourceEnum;
import com.den.we.admin.mapper.InterestTagMapper;
import com.den.we.admin.service.IInterestTagService;
import com.den.we.admin.service.ITagTypeService;
import com.den.we.admin.util.PageUtil;
import com.den.we.admin.vo.TagTableVo;
import com.den.we.entity.InterestTag;
import com.den.we.entity.TagType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author fatKarin
 * @since 2020-03-12
 */
@Service
public class InterestTagServiceImpl extends ServiceImpl<InterestTagMapper, InterestTag> implements IInterestTagService {

    @Resource
    private ITagTypeService typeService;

    @Override
    public IPage<TagTableVo> findTagsByPage(Integer currentPage, Integer pageSize) {
        //分页查询结果
        Page<InterestTag> page = new Page<>(currentPage,pageSize);
        // 返回结果
        Page<TagTableVo> result = new Page<>(currentPage,pageSize);

        result.setRecords(new ArrayList<>());
        //分页查询
        this.baseMapper.getTagsByPage(page);
        // 拷贝查询信息
        PageUtil.copyPageInfo(page,result);

        page.getRecords().forEach(x -> {
            TagType tagType = typeService.getById(x.getRootTypeId());
            String superiorName = this.baseMapper.findNameBySuperiorId(x.getSuperiorId());
            TagTableVo tagTableVo = TagTableVo.build(x,superiorName, tagType.getName());
            result.getRecords().add(tagTableVo);
        });

        return result;
    }

    @Override
    public boolean addNewTag(InterestTag interestTag) {
        // 检测标签是否存在
        QueryWrapper<InterestTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(InterestTag::getTagName, interestTag.getTagName());
        InterestTag queryTag = this.baseMapper.selectOne(queryWrapper);
        AssertUtil.isTrue(queryTag != null, MessageCode.TAG_ALREADY_EXITED);

        interestTag.setSource(TagSourceEnum.SYSTEM);
        interestTag.setCreateTime(new Date());
        return this.baseMapper.insert(interestTag) > 0;
    }
}
