package com.den.we.service;

import com.baomidou.mybatisplus.extension.service.IService;
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

    boolean addNewOne(Long communityId, String title, String content, Long creatorId);

    boolean thumpUp(Long articleId);
}
