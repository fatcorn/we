package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.TagSourceEnum;
import com.den.we.admin.mapper.InterestTagMapper;
import com.den.we.admin.service.IInterestTagService;
import com.den.we.entity.InterestTag;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author fatKarin
 * @since 2020-03-12
 */
@Service
public class InterestTagServiceImpl extends ServiceImpl<InterestTagMapper, InterestTag> implements IInterestTagService {

    @Override
    public IPage<InterestTag> findTagsByPage(Integer currentPage, Integer pageSize) {
        Page<InterestTag> page = new Page<>(currentPage,pageSize);
        return this.baseMapper.getTagsByPage(page);
    }

    @Override
    public boolean addNewTag(InterestTag interestTag) {
        interestTag.setSource(TagSourceEnum.SYSTEM);
        interestTag.setCreateTime(new Date());
        return this.baseMapper.insert(interestTag) > 0;
    }
}
