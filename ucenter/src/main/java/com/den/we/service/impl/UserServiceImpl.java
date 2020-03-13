package com.den.we.service.impl;

import com.den.we.IdWorkByTwitter;
import com.den.we.Vo.UserInfoVo;
import com.den.we.entity.User;
import com.den.we.mapper.UserMapper;
import com.den.we.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fatKarin
 * @since 2019-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

/*    @Resource
    private IdWorkByTwitter idWorkByTwitter;*/

    public boolean register(String phone, String password) {
        User user = new User();

        String saltNo = String.valueOf(new IdWorkByTwitter(24L, 24L).nextId());
        String salt = ByteSource.Util.bytes(saltNo).toHex().toLowerCase();
        String encryptPassword = new SimpleHash("md5", password, salt, 2).toHex().toLowerCase();

        user.setMobilePhone(phone);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        user.setUserName(phone);

        return this.save(user);
    }

    @Override
    public UserInfoVo getUserInfoVo(String retrieveInfo) {
        return this.baseMapper.retrieveUser(retrieveInfo);
    }

    public User findByPhone(String phone) {
        return this.baseMapper.findByPhone(phone);
    }

    @Override
    public User findByNameOrPhone(String phoneOrName) {
        return this.baseMapper.findByPhone(phoneOrName);
    }
}
