package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.UserFriends;
import com.den.we.mapper.UserFriendsMapper;
import com.den.we.service.IUserFriendsService;
import org.springframework.stereotype.Service;

/**
 * @author fatKarin
 * @since 2019-10-25
 */
@Service
public class UserFriendsServiceImpl extends ServiceImpl<UserFriendsMapper, UserFriends> implements IUserFriendsService {

}
