package com.den.we.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.SysAdminRole;
import com.den.we.entity.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-06
 */
public interface ISysAdminRoleService extends IService<SysAdminRole> {

    /**
     * 查询用户所有角色
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    void addSuperAdmin(String account, String password);
}
