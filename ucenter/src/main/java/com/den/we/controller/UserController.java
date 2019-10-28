package com.den.we.controller;


import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.Vo.UserInfoVo;
import com.den.we.entity.User;
import com.den.we.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private IUserService userService;

    /**
     * 检索用户
     * @param retrieveInfo      检索信息，手机号或用户名
     * @return                  UserInfoVo
     */
    @GetMapping("userRetrieve")
    public MessageRespResult<UserInfoVo> userRetrieve(String retrieveInfo) {

        AssertUtil.notEmpty(retrieveInfo, MessageCode.REQUIRED_PARAMETER);
        UserInfoVo userInfoVo = userService.getUserInfoVo(retrieveInfo);
        AssertUtil.notNull(userInfoVo, MessageCode.USER_NOT_EXIT);

        return  MessageRespResult.success4Data(userInfoVo);
    }

    public MessageRespResult addFriend() {
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
