package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.TagSourceEnum;
import com.den.we.entity.InterestTag;
import com.den.we.entity.UserInterestTag;
import com.den.we.mapper.InterestTagMapper;
import com.den.we.service.IInterestTagService;
import com.den.we.service.IUserInterestTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 兴趣标签服务类
 *
 * @author fatKarin
 * @date 2019/9/5 17:55
 */
@Service
@Slf4j
public class InterestTagImpl extends ServiceImpl<InterestTagMapper, InterestTag> implements IInterestTagService {

    @Resource
    private IUserInterestTagService userInterestTagService;

    /**
     * 用户新建兴趣标签
     * @param tagName       标签名称
     * @param comment       描述
     * @param creatorId     创建者id
     * @return              boolean
     */
    @Override
    @Transactional
    public boolean addOneByUser(String tagName, String comment, Long creatorId) {

        // 检查是否存在，存在并发问题
        InterestTag interestTag = this.baseMapper.findByTagName(tagName);
        AssertUtil.isTrue(interestTag == null, MessageCode.TAG_ALREADY_EXIT);

        interestTag = new InterestTag();
        interestTag.setTagName(tagName);
        interestTag.setTagComment(comment);
        interestTag.setUserId(creatorId);
        interestTag.setSource(TagSourceEnum.USER);
        interestTag.setCreateTime(new Date());

        int result =  this.baseMapper.insert(interestTag);
        AssertUtil.isTrue(result > 0, MessageCode.ERROR);

        // 给标签创建用户添加当前创建标签
        UserInterestTag userInterestTag = new UserInterestTag();
        userInterestTag.setCreateTime(new Date());
        userInterestTag.setUserId(creatorId);
        userInterestTag.setInterestId(interestTag.getId());

        return userInterestTagService.save(userInterestTag);
    }

    /**
     * 通过标签名称查找标签
     * @param tagName       标签名称
     * @return              InterestTag
     */
    @Override
    public InterestTag findTagByName(String tagName) {
        return this.baseMapper.findByTagName(tagName);
    }

    /**
     * 添加标签
     * @param userId         用户id
     * @param tagId          标签id
     * @return               boolean
     */
    @Override
    public boolean interestInTag(Long userId, Long tagId) {
        UserInterestTag userInterestTag = new UserInterestTag();
        userInterestTag.setCreateTime(new Date());
        userInterestTag.setUserId(userId);
        userInterestTag.setInterestId(tagId);

        return userInterestTagService.save(userInterestTag);
    }
}
