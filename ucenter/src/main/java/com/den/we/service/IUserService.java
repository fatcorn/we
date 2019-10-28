package com.den.we.service;

import com.den.we.Vo.UserInfoVo;
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

    /**
     * 手机密码简单注册
     * @param phone
     * @param password
     * @return
     */
    boolean register(String phone, String password);

    UserInfoVo getUserInfoVo(String retrieveInfo);

}
