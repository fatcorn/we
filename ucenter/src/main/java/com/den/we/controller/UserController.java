package com.den.we.controller;


import com.den.we.AssertUtil;
import com.den.we.CommonEnum;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.Vo.UserInfoVo;
import com.den.we.entity.FriendRequest;
import com.den.we.entity.User;
import com.den.we.entity.UserFriends;
import com.den.we.service.IFriendRequestService;
import com.den.we.service.IUserFriendsService;
import com.den.we.service.IUserService;
import com.den.we.transform.AuthidUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.den.we.constant.SessionAttribute.SESSION_USER_INFO;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
@Api(tags={"用户信息与操作接口"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IFriendRequestService friendRequestService;

    @Resource
    private IUserFriendsService userFriendsService;

    /**
     * 检索用户，增加用户名策略，用户名不能全为数字,以此来做正则，判断用户检索信息为手机号还是用户名
     * @param retrieveInfo      检索信息，手机号或用户名
     * @return                  UserInfoVo
     */
    @ApiOperation(value = "检索用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "检索信息，手机号或用户名", name = "retrieveInfo", dataTypeClass = String.class, required = true),
    })
    @GetMapping("/userRetrieve")
    public MessageRespResult<UserInfoVo> userRetrieve(String retrieveInfo) {

        AssertUtil.notEmpty(retrieveInfo, MessageCode.REQUIRED_PARAMETER);
        UserInfoVo userInfoVo = userService.getUserInfoVo(retrieveInfo);
        AssertUtil.notNull(userInfoVo, MessageCode.USER_NOT_EXIT);

        return  MessageRespResult.success4Data(userInfoVo);
    }

    /**
     * 添加好友请求,已通过go-chat代替
     * @param userInfo      userInfo
     * @param retrieveInfo  检索信息，手机号或用户名
     * @return              MessageRespResult
     */
    @Deprecated
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
     * 查询用户联系人列表
     * @param  userInfo 用户session信息
     * @return List<UserFriends>
     */
    @ApiOperation(value = "查询用户联系人列表")
    @ApiImplicitParams({
    })
    @GetMapping("getContacts")
    public MessageRespResult<List<UserFriends>> getContacts(@SessionAttribute(SESSION_USER_INFO) AuthidUserInfo userInfo) {
        return MessageRespResult.success4Data(userFriendsService.findByUserId(userInfo.getId()));
    }

    /**
     * 查询用户好友请求历史
     * @param  userInfo 用户session信息
     * @return List<UserFriends>
     */
    @ApiOperation(value = "查询用户好友请求历史")
    @ApiImplicitParams({
    })
    @GetMapping("getFriendRequest")
    public MessageRespResult<List<FriendRequest>> getFriendRequest(@SessionAttribute(SESSION_USER_INFO) AuthidUserInfo userInfo) {
        return MessageRespResult.success4Data(friendRequestService.findRecordsByUserId(userInfo.getId()));
    }

    /**
     * 处理用户好友请求操作
     * @param requestId     请求id
     * @param result        操作结果，同意or不同意
     * @return
     */
    @GetMapping("handFriendRequest")
    public MessageRespResult handFriendRequest(Long requestId, CommonEnum result) {
        AssertUtil.notNull(requestId,MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(result,MessageCode.REQUIRED_PARAMETER);
        //出理用户操作
        friendRequestService.handleRequest(requestId,result);
        return MessageRespResult.success();
    }

    /**
     * 获取用户信息
     * @param userId    userId
     * @return          MessageRespResult
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
