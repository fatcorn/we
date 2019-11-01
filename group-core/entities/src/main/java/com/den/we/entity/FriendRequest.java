package com.den.we.entity;

import com.den.we.FriendRequestStatusEnum;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 好友添加请求(FriendRequest)实体类
 *
 * @author makejava
 * @since 2019-10-24 16:11:09
 */
@Data
public class FriendRequest implements Serializable {

    //id
    private Long id;

    //请求用户id
    private Long requestId;

    //target_id
    private Long targetId;

    //状态{0:已拒绝,1:验证中,2:已接受,3:已过期}
    private FriendRequestStatusEnum status;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}