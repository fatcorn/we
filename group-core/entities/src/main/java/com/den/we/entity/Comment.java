package com.den.we.entity;

import com.den.we.ArticleTypeEnum;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author fatKarin
 * @since 2019-10-16 12:11:26
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -76024125332151938L;

    //id
    private Long id;

    //文章iid
    private Long articleId;

    //{0:文字，1:图片，2语音，3：视频}
    private ArticleTypeEnum commentType;

    // 支持人数
    private Integer likeNum;

    // 不支持人数
    private Integer dislikeNum;

    // 举报人数
    private Integer reportedNum;

    //评论内容
    private String content;

    //评论人id
    private Long commentId;

    //评论时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}