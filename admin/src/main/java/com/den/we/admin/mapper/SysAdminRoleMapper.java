package com.den.we.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.den.we.entity.SysAdminRole;
import com.den.we.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  SysAdminRoleMapper 接口
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-06
 */

public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    /**
     * 查找用户下所有角色
     * @param adminId
     * @return
     */
    List<SysRole> findRolesByAdminId(@Param("adminId") Long adminId);

}
