package com.den.we.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.entity.Article;
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

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 更新点赞数
     * @param articleId
     * @return
     */
    @Update("update article set like_num = like_num + 1 where id=#{articleId}")
    int updateLikeCount(@Param("articleId") Long articleId);

    /**
     * 更新不推荐数
     * @param articleId
     * @return
     */
    @Update("update article set dislike_num = dislike_num + 1 where id=#{articleId}")
    int updateDislikeCount(@Param("articleId") Long articleId);
}
