package com.den.we.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.UserFriends;
import com.den.we.mapper.UserFriendsMapper;
import com.den.we.service.IUserFriendsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fatKarin
 * @since 2019-10-25
 */
@Service
public class UserFriendsServiceImpl extends ServiceImpl<UserFriendsMapper, UserFriends> implements IUserFriendsService {

    @Override
    public List<UserFriends> findByUserId(Long userId) {
        //构造条件，查询用户id
        QueryWrapper<UserFriends> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserFriends::getUserId, userId);
        return this.baseMapper.selectList(queryWrapper);
    }
}
