package com.den.we.service;

import com.den.we.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
public interface IUserService extends IService<User> {

    boolean register(String phone, String password);

    User findByPhone(String phone);
}
