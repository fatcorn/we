package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Community)实体类
 *
 * @author fatKarin
 * @since 2019-09-06 16:15:47
 */
@Data
public class Community implements Serializable {

    private Long id;

    //兴趣标签id
    private Long interestId;

    //已发贴数量
    private Integer postNum;

    //管理者id{为-1时，暂无版主，系统代管}
    private Long adminId;

    //社区名
    private String communityName;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}