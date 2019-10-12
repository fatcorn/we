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

    @Update("update article set support_num = support_num + 1 where id=#{articleId}")
    int updateLikeCount(@Param("articleId") Long articleId);
}
