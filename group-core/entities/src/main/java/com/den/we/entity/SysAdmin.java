package com.den.we.entity;

import com.den.we.CommonEnum;
import lombok.Data;

import java.util.Date;

/**
 * 管理员(SysAdmin)实体类
 *
 * @author fatKarin
 * @since 2020-03-06 16:58:37
 */
@Data
public class SysAdmin{
    //id
    private Long id;

    //账号
    private String account;

    //密码
    private String password;

    //盐
    private String salt;

    //邮箱
    private String email;

    //手机号
    private Long mobilePhone;

    //0：禁用，1：启用
    private CommonEnum status;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;
}