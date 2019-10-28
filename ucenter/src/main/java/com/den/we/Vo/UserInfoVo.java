package com.den.we.Vo;

import lombok.Data;

/**
 *
 */
@Data
public class UserInfoVo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 国家
     */
    private String country;

    /**
     * 城市
     */
    private String city;
}
