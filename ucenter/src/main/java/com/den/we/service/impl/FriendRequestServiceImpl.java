package com.den.we.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.FriendRequestStatusEnum;
import com.den.we.MessageRespResult;
import com.den.we.constant.MqtTopic;
import com.den.we.entity.FriendRequest;
import com.den.we.mapper.FriendRequestMapper;
import com.den.we.rpc.RelayRemoteService;
import com.den.we.service.IFriendRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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
}
