package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 标签别名(TagNickName)实体类
 *
 * @author fatKarin
 * @since 2019-09-06 11:25:07
 */
@Data
public class TagNickName implements Serializable {

    //id
    private Long id;

    //别名名称
    private String name;

    //标签id
    private Long tagId;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}