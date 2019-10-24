package com.den.we.controller;


import com.den.we.MessageRespResult;
import com.den.we.entity.User;
import com.den.we.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    public MessageRespResult register(User user) {
        return MessageRespResult.success();
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    public MessageRespResult<User> getUserInfo(String userId) {
        return MessageRespResult.success4Data(userService.getById(userId));
    }

    @RequestMapping("/hello")
    public String hello() {
        return "你好";
    }
}
