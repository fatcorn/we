package com.den.we.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.den.we.CommonEnum;
import com.den.we.TagSourceEnum;
import com.den.we.anotation.RequiredParam;
import com.den.we.entity.InterestTag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fatKarin
 * @date 2020/3/20 11:11
 */
@Data
@Builder
public class TagTableVo {
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

    //类型id
    private String rootType;

    //父类id
    private String superiorType;

    //根标签下一级为1，再下一级为2
    private Integer level;

    //俱乐部使用数
    private Long tagClubNum;

    //标签使用人数
    private Long tagUserNum;

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

    public static TagTableVo build(InterestTag interestTag,String superiorType,String rootType) {
        return TagTableVo.builder()
                .id(interestTag.getId())
                .tagName(interestTag.getTagName())
                .createTime(interestTag.getCreateTime())
                .level(interestTag.getLevel())
                .rootType(rootType)
                .superiorType(superiorType)
                .updateTime(interestTag.getUpdateTime())
                .tagClubNum(interestTag.getTagClubNum())
                .tagUserNum(interestTag.getTagUserNum())
                .source(interestTag.getSource())
                .tagComment(interestTag.getTagComment())
                .userId(interestTag.getUserId())
                .verified(interestTag.getVerified()).build();
    }
}
