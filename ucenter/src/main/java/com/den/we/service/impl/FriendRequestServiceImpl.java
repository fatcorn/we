package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.FriendRequest;
import com.den.we.mapper.FriendRequestMapper;
import com.den.we.service.IFriendRequestService;
import org.springframework.stereotype.Service;

/**
 * @author fatKarin
 * @since 2019-10-25
 */
@Service
public class FriendRequestServiceImpl extends ServiceImpl<FriendRequestMapper, FriendRequest> implements IFriendRequestService {

}
