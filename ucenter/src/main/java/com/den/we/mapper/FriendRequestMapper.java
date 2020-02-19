package com.den.we.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.FriendRequestStatusEnum;
import com.den.we.entity.FriendRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  FriendRequestMapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2019-10-25
 */

public interface FriendRequestMapper extends BaseMapper<FriendRequest> {

    @Update("update friend_request set status = #{status} where id = #{id}")
    int updateStatusById(@Param("id") long id, @Param("status") FriendRequestStatusEnum status);

}
