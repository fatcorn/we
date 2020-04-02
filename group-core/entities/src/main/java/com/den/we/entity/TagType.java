package com.den.we.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 标签根类型(TagType)实体类
 *
 * @author makejava
 * @since 2020-03-18 16:27:13
 */

@Data
public class TagType implements Serializable {

    private static final long serialVersionUID = 212930766328092361L;

    //id
    @TableId(type = IdType.AUTO)
    private Long id;

    //类型名称
    private String name;

    //类型描述
    private String comment;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}