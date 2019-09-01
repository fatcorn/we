package com.den.we.controller;

import com.den.we.entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/target")
    public Message greeting(Message message) throws Exception {
        // 模拟延时，以便测试客户端是否在异步工作
        Thread.sleep(1000);
        return new Message("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
    }
}
