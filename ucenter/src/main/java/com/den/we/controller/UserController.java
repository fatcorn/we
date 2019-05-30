package com.den.we.controller;


import com.den.we.MessageRespResult;
import com.den.we.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
@RestController
@RequestMapping("/uCenter")
public class UserController {

    public MessageRespResult register(User user) {
        return MessageRespResult.success();
    }
}
