package com.den.we.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author fatKarin
 * @date 2019/10/29 10:26
 */
@Service
public class MqService {

    @RabbitListener(queues = "fanout.queue")
    public void receive(Message message) {
        System.out.println("收到消息 : " + new String(message.getBody()));

    }

}
