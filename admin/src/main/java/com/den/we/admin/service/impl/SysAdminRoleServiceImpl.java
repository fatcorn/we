package com.den.we.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.den.we.CommonEnum;
import com.den.we.admin.mapper.SysAdminRoleMapper;
import com.den.we.admin.service.ISysAdminRoleService;
import com.den.we.admin.service.ISysAdminService;
import com.den.we.admin.service.ISysRoleService;
import com.den.we.entity.SysAdmin;
import com.den.we.entity.SysAdminRole;
import com.den.we.entity.SysRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author fatKarin
 * @since 2020-03-06
 */
@Service
public class SysAdminRoleServiceImpl extends ServiceImpl<SysAdminRoleMapper, SysAdminRole> implements ISysAdminRoleService {

    @Resource
    private SysAdminRoleMapper mapper;

    @Resource
    private ISysAdminService adminService;

    @Resource
    private ISysRoleService roleService;

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return mapper.findRolesByAdminId(userId);
    }

    @Override
    @Transactional
    public void addSuperAdmin(String account, String password) {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setAccount(account);
        String encryptPassword = new BCryptPasswordEncoder().encode(password);
        sysAdmin.setPassword(encryptPassword);
        sysAdmin.setStatus(CommonEnum.ENABLE);
        sysAdmin.setCreateTime(new Date());
        adminService.save(sysAdmin);

        SysRole role = new SysRole();
        role.setRoleName("SUPER");
        role.setStatus(CommonEnum.ENABLE);
        role.setCreateTime(new Date());
        roleService.save(role);

        SysAdminRole adminRole = new SysAdminRole();
        adminRole.setAdminId(sysAdmin.getId());
        adminRole.setRoleId(role.getId());
        adminRole.setCreateTime(new Date());

        this.baseMapper.insert(adminRole);

    }
}
