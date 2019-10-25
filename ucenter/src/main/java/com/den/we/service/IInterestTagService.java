package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.InterestTag;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
public interface IInterestTagService extends IService<InterestTag> {


    /**
     * 用户新建兴趣标签
     * @param tagName       标签名称
     * @param comment       描述
     * @param creatorId     创建者id
     * @return              boolean
     */
    boolean addOneByUser(String tagName, String comment, Long creatorId);

    /**
     * 通过标签名称查找标签
     * @param tagName       标签名称
     * @return              InterestTag
     */
    InterestTag findTagByName(String tagName);

    /**
     * 添加标签
     * @param userId         用户id
     * @param tagId          标签id
     * @return               boolean
     */
    boolean interestInTag(Long userId, Long tagId);
}
