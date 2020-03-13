package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.CommonEnum;
import com.den.we.admin.mapper.SysRoleMapper;
import com.den.we.admin.service.ISysRoleService;
import com.den.we.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author fatKarin
 * @since 2020-03-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public boolean addNewRole(String roleName) {
        SysRole role = new SysRole();
        role.setRoleName(roleName);
        role.setStatus(CommonEnum.ENABLE);
        role.setCreateTime(new Date());
        return this.baseMapper.insert(role) > 0;
    }
}
