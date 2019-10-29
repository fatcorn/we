package com.den.we.controller;

import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.entity.InterestTag;
import com.den.we.service.IInterestTagService;
import com.den.we.transform.AuthidUserInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.den.we.constant.SessionAttribute.SESSION_USER_INFO;

/**
 *
 * @author fatKarin
 * @date 2019/9/6 10:17
 */
@RestController
@RequestMapping("/tag")
public class InterestTagController {

    @Resource
    private IInterestTagService interestTagService;


    /**
     * 用户创建新标签
     * @param interestTag   标签名称
     * @param comment       描述
     * @return              MessageRespResult
     */
    @PostMapping("/customerAdd")
    public MessageRespResult customerAdd(@SessionAttribute(SESSION_USER_INFO) AuthidUserInfo userInfo, String interestTag, String comment) {

        AssertUtil.notEmpty(interestTag, MessageCode.REQUIRED_PARAMETER);

        boolean result = interestTagService.addOneByUser(interestTag, comment, userInfo.getId());
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }

    /**
     * 添加标签
     * @param userInfo  userInfo
     * @param tagName   标签信息
     * @return
     */
    @GetMapping("/interestInTag")
    public MessageRespResult interestInTag(@SessionAttribute(SESSION_USER_INFO) AuthidUserInfo userInfo, String tagName) {

        AssertUtil.notEmpty(tagName, MessageCode.REQUIRED_PARAMETER);

        // 查找标签是否存在
        InterestTag interestTag = interestTagService.findTagByName(tagName);
        AssertUtil.notNull(interestTag, MessageCode.ERROR);

        boolean result =  interestTagService.interestInTag(userInfo.getId(), interestTag.getId());
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }
}
