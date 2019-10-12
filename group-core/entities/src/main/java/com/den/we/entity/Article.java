package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 帖子(Posts)实体类
 *
 * @author fatKarin
 * @since 2019-09-06 16:17:11
 */
@Data
public class Article implements Serializable {
    // id
    private Long id;

    //社区id
    private Long communityId;

    //题目
    private String title;

    //内容
    private String content;

    //评论数
    private Integer commentNum;

    //支持人数
    private Integer supportNum;

    //不支持人数,决定是否是水贴，触发删帖，排位
    private Integer unsupportNum;

    //被举报次数，触发删帖，封号等操作
    private Integer reportedTime;

    //发帖人id
    private Long creatorId;

    //帖子类型{，0：文字,1：图片, 2 : 音频，3：视频}
    private Integer articleType;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}