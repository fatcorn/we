package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.CommonEnum;
import com.den.we.admin.mapper.SysAdminMapper;
import com.den.we.admin.service.ISysAdminService;
import com.den.we.entity.SysAdmin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author fatKarin
 * @since 2020-03-06
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    @Override
    public SysAdmin getByName(String adminName) {
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysAdmin::getAccount,adminName);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addNew(String account, String password) {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAccount(account);
        String encryptPassword = new BCryptPasswordEncoder().encode(password);
        sysAdmin.setAccount(encryptPassword);
        sysAdmin.setStatus(CommonEnum.ENABLE);
        sysAdmin.setCreateTime(new Date());

        return this.baseMapper.insert(sysAdmin) > 0;
    }
}
