package com.den.we.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 提供mqtt 发布服务
 * @author fatKarin
 * @date 2019/10/31 9:27
 */
public interface IPublishService {
    /**
     * http publish
     * @return
     * @throws InterruptedException
     */
    Object publish();

    /**
     * matt 消息推送服务
     * @param topic         主题
     * @param payload       消息内容
     * @return              boolean
     */
    @RequestMapping(value = "/tcpPublish",method = RequestMethod.POST)
    boolean tcpPublish(@RequestParam("topic")String topic, @RequestParam("payload")String payload);

}
