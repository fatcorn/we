package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户分组标签(GroupTag)实体类
 *
 * @author makejava
 * @since 2019-10-24 16:11:52
 */
@Data
public class GroupTag implements Serializable {

    private static final long serialVersionUID = -44320444179871906L;

    //id
    private Long id;

    //用户id
    private Long userId;

    //分组标签名
    private String tagName;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}