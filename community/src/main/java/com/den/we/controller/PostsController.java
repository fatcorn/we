package com.den.we.controller;

import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.service.IPostsService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author fatKarin
 * @date 2019/9/9 16:12
 */
@RestController
@RequestMapping("posts")
public class PostsController {

    @Resource
    private IPostsService iPostsService;

    /**
     * 新增帖子
     * @param communityId   社区id
     * @param title         标题
     * @param content       内容
     * @param creatorId     创建者id
     * @return  MessageRespResult
     */
    @PostMapping("/post")
    public MessageRespResult post(Long communityId, String title, String content, Long creatorId) {

        boolean result = iPostsService.addNewOne(communityId, title, content, creatorId);
        Assert.isTrue(result, "参数错误");
        return MessageRespResult.success();
    }

    /**
     * 点赞操作
     * @param postsId
     * @return
     */
    @RequestMapping("/thumpUp")
    public MessageRespResult thumpUp(Long postsId) {
        boolean result = iPostsService.thumpUp(postsId);
        Assert.isTrue(result, "参数错误");
        return MessageRespResult.success();
    }


}
