package com.den.we.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 管理员角色中间表(SysAdminRole)实体类
 *
 * @author fatKarin
 * @since 2020-03-06 16:58:37
 */
@Data
public class SysAdminRole implements Serializable {
    //id
    private Long id;

    //管理员id
    private Long adminId;

    //角色id
    private Long roleId;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}