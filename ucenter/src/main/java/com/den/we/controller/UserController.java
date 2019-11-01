package com.den.we.controller;


import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.Vo.UserInfoVo;
import com.den.we.entity.User;
import com.den.we.service.IFriendRequestService;
import com.den.we.service.IUserService;
import com.den.we.transform.AuthidUserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.den.we.constant.SessionAttribute.SESSION_USER_INFO;

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

    @Resource
    private IFriendRequestService friendRequestService;

    /**
     * 检索用户，增加用户名策略，用户名不能全为数字,以此来做正则，判断用户检索信息为手机号还是用户名
     * @param retrieveInfo      检索信息，手机号或用户名
     * @return                  UserInfoVo
     */
    @GetMapping("/userRetrieve")
    public MessageRespResult<UserInfoVo> userRetrieve(String retrieveInfo) {

        AssertUtil.notEmpty(retrieveInfo, MessageCode.REQUIRED_PARAMETER);
        UserInfoVo userInfoVo = userService.getUserInfoVo(retrieveInfo);
        AssertUtil.notNull(userInfoVo, MessageCode.USER_NOT_EXIT);

        return  MessageRespResult.success4Data(userInfoVo);
    }

    @GetMapping("/newFriendRequest")
    public MessageRespResult newFriendRequest(@SessionAttribute(SESSION_USER_INFO) AuthidUserInfo userInfo,String retrieveInfo) {
        AssertUtil.notEmpty(retrieveInfo, MessageCode.REQUIRED_PARAMETER);

        User friendUser = userService.findByNameOrPhone(retrieveInfo);
        AssertUtil.notNull(friendUser, MessageCode.ERROR);

        boolean result = friendRequestService.addNewRequest(friendUser.getId(), userInfo.getId());

        AssertUtil.isTrue(result, MessageCode.ERROR);
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
