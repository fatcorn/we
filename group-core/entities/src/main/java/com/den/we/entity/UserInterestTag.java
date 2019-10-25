package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户兴趣标签(UserInterestTag)实体类
 *
 * @author fatKarin
 * @since 2019-10-25 16:29:30
 */
@Data
public class UserInterestTag implements Serializable {

    //id
    private Long id;

    //用户id
    private Long userId;

    //兴趣标签id
    private Long interestId;

    //创建时间
    private Date createTime;
}