package com.den.we.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2019-09-09
 */

public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 更新点赞数
     * @param commentId 评论id
     * @return int
     */
    @Update("update comment set like_num = like_num + 1 where id=#{commentId}")
    int updateLikeCount(@Param("commentId") Long commentId);

    /**
     * 更新不推荐数
     * @param commentId 评论id
     * @return int
     */
    @Update("update comment set dislike_num = dislike_num + 1 where id=#{commentId}")
    int updateDislikeCount(@Param("commentId") Long commentId);
}
