package com.den.we.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.den.we.entity.SysAdmin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2020-03-06
 */
public interface ISysAdminService extends IService<SysAdmin> {

    /**
     * 通过用户名查询
     * @param adminName
     * @return
     */
    SysAdmin getByName(String adminName);

    /**
     * 新增管理员
     * @param account
     * @param password
     * @return
     */
    boolean addNew(String account, String password);
}
