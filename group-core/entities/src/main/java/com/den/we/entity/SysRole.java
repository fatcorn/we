package com.den.we.entity;

import com.den.we.CommonEnum;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 管理员角色(SysRole)实体类
 *
 * @author fatKarin
 * @since 2020-03-06 16:58:37
 */
@Data
public class SysRole implements Serializable {
    //id
    private Long id;

    //角色名称
    private String roleName;

    //0：禁用，1：启用
    private CommonEnum status;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;



}