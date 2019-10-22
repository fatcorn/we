package com.den.we.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.ArticleTypeEnum;
import com.den.we.entity.Comment;
import com.den.we.mapper.CommentMapper;
import com.den.we.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author fatKarin
 * @date 2019/9/9 16:06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {


    @Override
    public boolean addNewOne(Long articleId, String content, Long creatorId, ArticleTypeEnum type) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentType(type);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        return this.baseMapper.insert(comment) > 0;
    }

    @Override
    public boolean thumpUp(Long commentId) {
        return this.baseMapper.updateLikeCount(commentId) > 0;
    }

    @Override
    public boolean dislike(Long commentId) {
        return this.baseMapper.updateDislikeCount(commentId) > 0;
    }
}
