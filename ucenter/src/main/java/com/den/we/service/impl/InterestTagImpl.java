package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.InterestTag;
import com.den.we.entity.TagNickName;
import com.den.we.entity.User;
import com.den.we.mapper.InterestTagMapper;
import com.den.we.service.IInterestTagService;
import com.den.we.service.ITagNickNameService;
import com.den.we.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 兴趣标签服务类
 *
 * @author fatKarin
 * @date 2019/9/5 17:55
 */
@Service
public class InterestTagImpl extends ServiceImpl<InterestTagMapper, InterestTag> implements IInterestTagService {

    @Resource
    private ITagNickNameService tagNickNameService;

    @Override
    @Transactional
    public boolean addOne(InterestTag interestTag, String nickName) {
        int result = this.baseMapper.insert(interestTag);
        Assert.isTrue(result > 0, "参数错误");
        TagNickName tagNickName = new TagNickName();
        tagNickName.setName(nickName);
        tagNickName.setTagId(interestTag.getId());
        return tagNickNameService.save(tagNickName);
    }
}
