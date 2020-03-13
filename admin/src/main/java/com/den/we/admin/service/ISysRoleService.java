package com.den.we.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.SysRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-06
 */
public interface ISysRoleService extends IService<SysRole> {

    boolean addNewRole(String roleName);
}
