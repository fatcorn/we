package com.den.we.entity;

import com.den.we.CommonEnum;
import com.den.we.TagSourceEnum;
import lombok.Data;

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
    private Long id;

    //标签名称
    private String tagName;

    //标签描述
    private String tagComment;

    //创建id，为-1则来自系统

    private Long userId;

    //标签来源{0:system,1:user}
    private TagSourceEnum source;

    //祖先id 为0 表示 这是一个大类
    private Long ancientId;

    //俱乐部使用数
    private Long tagClubNum;

    //标签使用人数
    private Long tagUserNum;

    //是否验证 0 否， 1 是
    private CommonEnum verified;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}