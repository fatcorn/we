package com.den.we.controller;

import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.service.IPublishService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fatKarin
 * @date 2019/11/1 11:29
 */
@RestController
@RequestMapping("/relay")
public class MqttTestController {

    @Resource
    private IPublishService publishService;

    /**
     * 发布消息
     * @param topic
     * @param payload
     * @return
     */
    @PostMapping("/publishMessage")
    public MessageRespResult publishMessage(String topic, String payload) {

       AssertUtil.notEmpty(topic, MessageCode.REQUIRED_PARAMETER);
       boolean result = publishService.tcpPublish(topic, payload);
       AssertUtil.isTrue(result, MessageCode.PUBLISH_FAILED);
       return MessageRespResult.success();
    }

}
