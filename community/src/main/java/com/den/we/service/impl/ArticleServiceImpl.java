package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.entity.Article;
import com.den.we.mapper.ArticleMapper;
import com.den.we.service.IArticleService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author fatKarin
 * @date 2019/9/9 16:06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Override
    public boolean addNewOne(Long communityId, String title, String content, Long creatorId) {
        Article article = new Article();
        article.setCommunityId(communityId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreatorId(creatorId);
        article.setCreateTime(Calendar.getInstance().getTime());

        int result = baseMapper.insert(article);
        return result > 0;
    }

    @Override
    public boolean thumpUp(Long articleId) {
        int result = baseMapper.updateLikeCount(articleId);
        return result > 0;
    }

}
