package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.ArticleTypeEnum;
import com.den.we.entity.Article;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
public interface IArticleService extends IService<Article> {

    /**
     * 新增文章
     * @param communityId   社区id
     * @param title         文章题目
     * @param content       内容
     * @param creatorId     创建人id
     * @param type          文章类型
     * @return              boolean
     */
    boolean addNewOne(Long communityId, String title, String content, Long creatorId, ArticleTypeEnum type);

    /**
     * 点赞文章
     * @param articleId     文章id
     * @return              boolean
     */
    boolean thumpUp(Long articleId);

    /**
     * 不推荐文章
     * @param articleId     文章id
     * @return              boolean
     */
    boolean dislike(Long articleId);
}
