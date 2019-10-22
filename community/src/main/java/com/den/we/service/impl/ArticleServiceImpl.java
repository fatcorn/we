package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.ArticleTypeEnum;
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


    /**
     * 新增文章
     * @param communityId   社区id
     * @param title         文章题目
     * @param content       内容
     * @param creatorId     创建人id
     * @param type          文章类型
     * @return              boolean
     */
    @Override
    public boolean addNewOne(Long communityId, String title, String content, Long creatorId, ArticleTypeEnum type) {
        Article article = new Article();
        article.setCommunityId(communityId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreatorId(creatorId);
        article.setCreateTime(Calendar.getInstance().getTime());
        article.setArticleType(type);

        int result = baseMapper.insert(article);
        return result > 0;
    }

    /**
     * 点赞文章
     * @param articleId     文章id
     * @return              boolean
     */
    @Override
    public boolean thumpUp(Long articleId) {
        int result = baseMapper.updateLikeCount(articleId);
        return result > 0;
    }

    /**
     * 不推荐文章
     * @param articleId     文章id
     * @return              boolean
     */
    @Override
    public boolean dislike(Long articleId) {
        int result = baseMapper.updateDislikeCount(articleId);
        return result > 0;
    }

}
