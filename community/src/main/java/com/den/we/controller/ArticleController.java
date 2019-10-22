package com.den.we.controller;

import com.den.we.ArticleTypeEnum;
import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.service.IArticleService;
import com.den.we.service.ICommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author fatKarin
 * @date 2019/9/9 16:12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService iArticleService;

    @Resource
    private ICommentService iCommentService;

    /**
     * 新增帖子
     * @param communityId   社区id
     * @param title         标题
     * @param content       内容
     * @param creatorId     创建者id
     * @return              MessageRespResult
     */
    @PostMapping("/addNewArticle")
    public MessageRespResult addNewArticle(Long communityId, String title, String content, Long creatorId, ArticleTypeEnum type) {

        // 非空校验
        AssertUtil.notNull(communityId, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(title, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(content, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(creatorId, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(type, MessageCode.REQUIRED_PARAMETER);

        boolean result = iArticleService.addNewOne(communityId, title, content, creatorId, type);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     * 点赞操作
     * @param articleId     文章id
     * @return              MessageRespResult
     */
    @PostMapping("/thumpUp")
    public MessageRespResult thumpUp(Long articleId) {
        AssertUtil.notNull(articleId, MessageCode.REQUIRED_PARAMETER);
        boolean result = iArticleService.thumpUp(articleId);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     * 不推荐文章
     * @param  articleId     文章id
     * @return              MessageRespResult
     */
    @PostMapping("/dislike")
    public MessageRespResult dislike(Long articleId) {
        AssertUtil.notNull(articleId, MessageCode.REQUIRED_PARAMETER);
        boolean result = iArticleService.dislike(articleId);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     * 新增评论
     * @param articleId     社区id
     * @param content       内容
     * @param creatorId     创建者id
     * @param type          类型
     * @return              MessageRespResult
     */
    @PostMapping("/comment/addNewComment")
    public MessageRespResult addNewComment(Long articleId, String content, Long creatorId, ArticleTypeEnum type) {
        // 非空校验
        AssertUtil.notNull(articleId, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(content, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(creatorId, MessageCode.REQUIRED_PARAMETER);
        AssertUtil.notNull(type, MessageCode.REQUIRED_PARAMETER);

        boolean result = iCommentService.addNewOne(articleId,content,creatorId,type);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     * 点赞评论
     * @param CommentId     文章id
     * @return              MessageRespResult
     */
    @PostMapping("/comment/thumpUpComment")
    public MessageRespResult thumpUpComment(Long CommentId) {
        AssertUtil.notNull(CommentId, MessageCode.REQUIRED_PARAMETER);
        boolean result = iCommentService.thumpUp(CommentId);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     * 不推荐文章
     * @param  CommentId     文章id
     * @return              MessageRespResult
     */
    @PostMapping("/comment/dislike")
    public MessageRespResult dislikeComment(Long CommentId) {
        AssertUtil.notNull(CommentId, MessageCode.REQUIRED_PARAMETER);
        boolean result = iCommentService.dislike(CommentId);
        AssertUtil.isTrue(result, MessageCode.SQL_EXECUTE_FAILED);
        return MessageRespResult.success();
    }

    /**
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadResources")
    public MessageRespResult uploadResources(@RequestParam(value = "file") MultipartFile file) {
        AssertUtil.notNull(file, MessageCode.UPLOAD_FILE_NOT_BE_NULL);

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getName();

        String path = "D:/fileUpload/" +fileName;

        //创建文件路径
        File dest = new File(path);

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目

        } catch (IOException e) {
            return MessageRespResult.error(MessageCode.UPLOAD_FAILED);
        }

        return MessageRespResult.success4Data(path);
    }



}
