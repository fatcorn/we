package com.den.we.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户加入俱乐部(UserClub)实体类
 *
 * @author makejava
 * @since 2019-10-24 16:12:30
 */
public class UserClub implements Serializable {

    private static final long serialVersionUID = 176550040401481655L;

    //id
    private Long id;

    //用户id
    private Long userId;

    //已加入俱乐部id
    private Long clubId;

    //加入俱乐部时间
    private Date createTime;

}