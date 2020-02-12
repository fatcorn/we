package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.UserFriends;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-10-25
 */
public interface IUserFriendsService extends IService<UserFriends> {

    /**
     * 通过用户id查询用户联系人
     * @param userId
     * @return
     */
    List<UserFriends> findByUserId(Long userId);
}
