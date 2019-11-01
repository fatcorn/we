package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.FriendRequest;
import com.den.we.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-10-25
 */
public interface IFriendRequestService extends IService<FriendRequest> {

    /**
     * 新建添加好友请求
     * @param friendId      好友id
     * @param userId        自己id
     * @return              boolean
     */
    boolean addNewRequest(Long friendId, Long userId);
}
