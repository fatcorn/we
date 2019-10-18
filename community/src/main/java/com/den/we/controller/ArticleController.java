package com.den.we.controller;

import com.den.we.AssertUtil;
import com.den.we.MessageCode;
import com.den.we.MessageRespResult;
import com.den.we.service.IArticleService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author fatKarin
 * @date 2019/9/9 16:12
 */
@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Resource
    private IArticleService iArticleService;

    /**
     * 新增帖子
     * @param communityId   社区id
     * @param title         标题
     * @param content       内容
     * @param creatorId     创建者id
     * @return  MessageRespResult
     */
    @PostMapping("/addNewArticle")
    public MessageRespResult addNewArticle(Long communityId, String title, String content, Long creatorId) {

        boolean result = iArticleService.addNewOne(communityId, title, content, creatorId);
        AssertUtil.isTrue(result, MessageCode.ERROR);
        return MessageRespResult.success();
    }

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

    /**
     * 点赞操作
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/thumpUp")
    public MessageRespResult thumpUp(Long articleId) {
        boolean result = iArticleService.thumpUp(articleId);
        Assert.isTrue(result, "参数错误");
        return MessageRespResult.success();
    }

}
