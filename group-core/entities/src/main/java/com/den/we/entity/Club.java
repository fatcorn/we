package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 部落(Posts)实体类
 *
 * @author fatKarin
 * @since 2019-09-06 16:17:11
 */
@Data
public class Club implements Serializable {

    //id
    private Long id;

    //俱乐部名
    private String clubName;

    //兴趣便签id
    private Long interestTagId;

    //群主id
    private Long adminUserId;

    //俱乐部人数
    private Integer clubPeopleNum;

    //国家
    private String country;

    //省,州     
    private String province;

    //城市
    private String city;

    //郡县
    private String county;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;


}