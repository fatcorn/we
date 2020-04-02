package com.den.we.admin.controller;

import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageResp;
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
    public MessageResp addSuperAdmin(String account, String password) {
        AssertUtil.notEmpty(account, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notEmpty(password,MessageCode.REQUIRED_PARAMETER);
        adminRoleService.addSuperAdmin(account,password);
        return MessageResp.success();
    }

    @PostMapping("login")
    public MessageResp login(String account, String password) {
        return MessageResp.success("success");
    }

}
