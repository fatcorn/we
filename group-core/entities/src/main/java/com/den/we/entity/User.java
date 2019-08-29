package com.den.we.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author fatKarin
 * @since 2019-08-29 17:53:20
 */
@Data
public class User implements Serializable {

    //id
    private Long id;

    //用户账号
    private String userName;

    //昵称
    private String nickName;

    //密码
    private String password;

    //盐
    private String salt;

    //邮箱
    private String email;

    //手机号
    private String mobilePhone;

    //性别t{0:男,1:女}
    private Integer sex;

    //状态{0:禁用,1:正常}
    private Integer status;

    //二维码
    private String qrCode;

    //头像地址
    private String picture;

    //所在国家或地区
    private String country;

    //所在省或州
    private String province;

    //所在城市
    private String city;

    //所在区县
    private String county;

    //创建时间
    private Long createTime;

    //更新时间
    private Long updateTime;
}