package com.den.we.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.CommonEnum;
import com.den.we.FriendRequestStatusEnum;
import com.den.we.MessageRespResult;
import com.den.we.constant.MqtTopic;
import com.den.we.entity.FriendRequest;
import com.den.we.entity.UserFriends;
import com.den.we.mapper.FriendRequestMapper;
import com.den.we.rpc.RelayRemoteService;
import com.den.we.service.IFriendRequestService;
import com.den.we.service.IUserFriendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fatKarin
 * @since 2019-10-25
 */
@Service
@Slf4j
public class FriendRequestServiceImpl extends ServiceImpl<FriendRequestMapper, FriendRequest> implements IFriendRequestService {

    @Resource
    private RelayRemoteService relayRemoteService;

    @Resource
    private IUserFriendsService userFriendsService;

    @Override
    public boolean addNewRequest(Long friendId, Long userId) {

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequestId(userId);
        friendRequest.setTargetId(friendId);
        friendRequest.setCreateTime(new Date());
        friendRequest.setStatus(FriendRequestStatusEnum.VERIFYING);
        boolean result = this.baseMapper.insert(friendRequest) > 0;

        try {
            // 消息发布 待完成 friendId 做摘要, 消息推送失败，进入消息队列
            String topic = MqtTopic.FRIEND_REQUEST + friendId;
            Map<String, String> messageMap = new HashMap<>();
            messageMap.put("requestId",String.valueOf(friendRequest.getId()));
            String message = JSONUtils.toJSONString(messageMap);

            MessageRespResult messageRespResult = relayRemoteService.publish(topic,message);
            log.info("status:" + messageRespResult.getCode()+ "");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return result;
    }

    @Override
    public List<FriendRequest> findRecordsByUserId(Long userId) {
        //构造条件，查询用户id
        QueryWrapper<FriendRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FriendRequest::getTargetId, userId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public void handleRequest(Long requestId, CommonEnum result) {
        if(result.equals(CommonEnum.ENABLE)) {
            // 更新状态，同意
            this.baseMapper.updateStatusById(requestId,FriendRequestStatusEnum.ACCEPTED);

            FriendRequest friendRequest = this.baseMapper.selectById(requestId);
            // 插入互相的好友列表
            UserFriends userFriends1 = new UserFriends();
            userFriends1.setFriendId(friendRequest.getRequestId());
            userFriends1.setUserId(friendRequest.getTargetId());
            userFriends1.setCreateTime(new Date());
            // 交换
            UserFriends userFriends2 = new UserFriends();
            userFriends2.setFriendId(friendRequest.getTargetId());
            userFriends2.setUserId(friendRequest.getRequestId());
            userFriends2.setCreateTime(new Date());

            userFriendsService.save(userFriends1);
            userFriendsService.save(userFriends2);
        } else {
            // 拒绝
            this.baseMapper.updateStatusById(requestId,FriendRequestStatusEnum.REFUSE);
        }
    }
}
