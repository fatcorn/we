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


    boolean addOne(InterestTag interestTag, String nickName);
}
