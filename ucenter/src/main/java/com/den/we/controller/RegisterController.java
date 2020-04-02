package com.den.we.controller;

import com.den.we.MessageResp;
import com.den.we.service.IUserService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private IUserService iUserService;

    /**
     * 简单注册
     * @param phoneNumber 手机号
     * @param password    密码
     * @return
     */
    @PostMapping("/registerByPhone")
    public MessageResp phoneRegister(String phoneNumber, String password) {
        Assert.notNull(phoneNumber, "手机号不为空");
        Assert.notNull(password, "密码不为空");

        boolean result = iUserService.register(phoneNumber, password);

        Assert.isTrue(result, "注册失败");
        return MessageResp.success("注册成功");
    }
}
