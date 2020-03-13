package com.den.we.admin.controller;

import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.admin.service.ISysAdminRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fatKarin
 * @date 2020/3/6 14:20
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private ISysAdminRoleService adminRoleService;

    @GetMapping("/helloWorld")
    @PreAuthorize("hasRole('SUPER')")
    public String sayHi() {
        return "helloWorld";
    }

    @PostMapping("/addSuperAdmin")
    public MessageRespResult addSuperAdmin(String account, String password) {
        AssertUtil.notEmpty(account, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notEmpty(password,MessageCode.REQUIRED_PARAMETER);
        adminRoleService.addSuperAdmin(account,password);
        return MessageRespResult.success();
    }

    @PostMapping("login")
    public MessageRespResult login(String account, String password) {
        return MessageRespResult.success("success");
    }

}
