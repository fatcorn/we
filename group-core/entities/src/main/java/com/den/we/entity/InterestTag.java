package com.den.we.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.den.we.CommonEnum;
import com.den.we.TagSourceEnum;
import com.den.we.anotation.RequiredParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (InterestTag)实体类
 *
 * @author fatKarin
 * @since 2019-09-05 17:45:37
 */
@Data
public class InterestTag implements Serializable {

    //id
    @TableId(type = IdType.AUTO)
    private Long id;

    //标签名称
    @RequiredParam
    private String tagName;

    //标签描述
    private String tagComment;

    //创建id，为-1则来自系统
    private Long userId;

    //标签来源{0:system,1:user}
    private TagSourceEnum source;

    //类型id
    private Long rootTypeId;

    //父类id
    private Long superiorId;

    //根标签下一级为1，再下一级为2
    private Integer level;

    //俱乐部使用数
    private Long tagClubNum;

    //标签使用人数
    private Long tagUserNum;

    //下级标签数
    private Integer childrenTagNum;

    //是否验证 0 否， 1 是
    private CommonEnum verified;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}