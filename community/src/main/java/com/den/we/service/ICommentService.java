package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.ArticleTypeEnum;
import com.den.we.entity.Comment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 新增评论
     * @param articleId     文章id
     * @param content       内容
     * @param creatorId     创建人id
     * @param type          评论类型
     * @return              boolean
     */
    boolean addNewOne(Long articleId, String content, Long creatorId, ArticleTypeEnum type);

    /**
     * 点赞评论
     * @param commentId     评论id
     * @return              boolean
     */
    boolean thumpUp(Long commentId);

    /**
     * 点灭评论
     * @param commentId     评论id
     * @return              boolean
     */
    boolean dislike(Long commentId);
}
