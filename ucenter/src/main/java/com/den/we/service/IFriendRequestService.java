package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.CommonEnum;
import com.den.we.entity.FriendRequest;

import java.util.List;

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

    /**
     * 查找用用户所有好友请求
     * @param userId
     * @return
     */
    List<FriendRequest> findRecordsByUserId(Long userId);

    /**
     * 好友请求操作
     * @param requestId
     * @return
     */
    void handleRequest(Long requestId, CommonEnum result);
}
