package com.den.we.controller;

import com.alibaba.fastjson.JSONArray;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.entity.User;
import com.den.we.service.IUserService;
import com.den.we.transform.AuthidUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.den.we.constant.SessionAttribute.SESSION_USER_INFO;

@Api(tags={"登录接口"})
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private IUserService iUserService;

    @ApiOperation(value = "登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phoneNumber", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(value = "密码", name = "password", dataTypeClass = String.class, required = true),
    })
    @RequestMapping("/login")
    public MessageRespResult login(HttpServletRequest request, String phoneNumber, String password) {
        AssertUtil.notNull(phoneNumber, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(password, MessageCode.REQUIRED_PARAMETER);

        System.out.println("=========sessionId" + request.getSession().getId());

        User user = iUserService.findByPhone(phoneNumber);
        AuthidUserInfo authidUser = AuthidUserInfo.build(user);
        String encryptPassword = new SimpleHash("md5", password, user.getSalt(), 2).toHex().toLowerCase();

        AssertUtil.isTrue(user.getPassword().equals(encryptPassword), MessageCode.INCORRECT_PASSWORD);
        request.getSession().setAttribute(SESSION_USER_INFO, authidUser);
        return MessageRespResult.success();
    }
}
