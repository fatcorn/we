package com.den.we.controller;

import com.den.we.IdWorkByTwitter;
import com.den.we.MessageRespResult;
import com.den.we.entity.User;
import com.den.we.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private IUserService iUserService;

    @PostMapping("/registerByPhone")
    public MessageRespResult phoneRegister(String phoneNumber, String password) {
        Assert.notNull(phoneNumber, "手机号不为空");
        Assert.notNull(password, "密码不为空");

        boolean result = iUserService.register(phoneNumber, password);

        Assert.isTrue(result, "注册失败");
        return MessageRespResult.success("注册成功");
    }
}
