package com.den.we.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
* <p>
    * 
    * </p>
*
* @author fatKarin
* @since 2019-05-30
*/
@Data
public class User {

    private Long id;

    /**
    * 用户账号
    */
    private String userName;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 密码
    */
    private String password;

    /**
    * 盐
    */
    private String salt;

    /**
    * 手机号
    */
    private String mobilePhone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 头像地址
    */
    private String photoUrl;

    /**
    * 二维码
    */
    private String qrCode;

    /**
    * 性别{0:男,1:女}
    */
    private Integer sex;

    /**
    * 所在国家或地区
    */
    private String country;

    /**
    * 所在省或州
    */
    private String province;

    /**
    * 所在城市
    */
    private String city;

    /**
    * 所在区县
    */
    private String county;

    /**
    * 状态{0:禁用,1:正常}
    */
    private Integer status;

    /**
    * jwt
    */
    private String token;

    /**
    * token过期时间
    */
    private Date tokenExpireTime;

    /**
    * 上一次登录设备
    */
    private String lastLoginFacility;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;


}
