package com.den.we.controller;

import com.den.we.MessageRespResult;
import com.den.we.entity.User;
import com.den.we.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private IUserService iUserService;

    @RequestMapping("/login")
    public MessageRespResult login(HttpServletRequest request, String phoneNumber, String password) {
        Assert.notNull(phoneNumber, "手机号不为空");
        Assert.notNull(password, "密码不为空");

        System.out.println("=========sessionId" + request.getSession().getId());

        User user = iUserService.findByPhone(phoneNumber);
        String encryptPassword = new SimpleHash("md5", password, user.getSalt(), 2).toHex().toLowerCase();

        request.getSession().getId();

        request.getSession().setAttribute("session", user);
        request.getSession().getAttribute("session");
        Assert.isTrue(user.getPassword().equals(encryptPassword), "密码错误");

        return MessageRespResult.success("登录成功");
    }
}
