package com.den.we.mapper;

import com.den.we.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where mobile_phone = #{phone}")
    User findByPhone(@Param("phone") String Phone);
}
