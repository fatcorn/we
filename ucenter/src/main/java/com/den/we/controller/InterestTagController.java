package com.den.we.controller;

import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.entity.InterestTag;
import com.den.we.entity.TagNickName;
import com.den.we.entity.User;
import com.den.we.service.IInterestTagService;
import com.den.we.service.ITagNickNameService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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



    @RequestMapping("/customerAdd")
    public MessageRespResult customerAdd(HttpServletRequest request, InterestTag interestTag, String nickName) {

        User user = (User) request.getSession().getAttribute("session");
        interestTag.setUserId(user.getId());

        boolean result = interestTagService.addOne(interestTag, nickName);
        Assert.isTrue(result, "新增失败");
        return MessageRespResult.success("新增成功");
    }
}
