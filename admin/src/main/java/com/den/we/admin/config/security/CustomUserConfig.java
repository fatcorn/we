package com.den.we.admin.config.security;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.den.we.admin.constant.RoleNameConstant;
import com.den.we.admin.service.ISysAdminRoleService;
import com.den.we.admin.service.ISysAdminService;
import com.den.we.entity.SysAdmin;
import com.den.we.entity.SysRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 替换为数据库中的user
 * @author fatKarin
 * @date 2020/3/9 10:41
 */
@Component
public class CustomUserConfig implements UserDetailsService {

    @Resource
    private ISysAdminService adminService;

    @Resource
    private ISysAdminRoleService adminRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysAdmin admin = adminService.getByName(userName);
        if (admin == null) {
            throw new UsernameNotFoundException("不是新应用管理平台的用户!请联系管理员添加权限!");
        }
        //构建权限
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

//        // 每位用户都默认拥有的角色
//        grantedAuthorities.add(new SimpleGrantedAuthority(RoleNameConstant.ROLE_ADMIN));
        //查询用户角色
        List<SysRole> roles = adminRoleService.findRolesByUserId(admin.getId());

        if (CollectionUtils.isNotEmpty(roles)) {
            for (SysRole sysRole : roles) {
                GrantedAuthority ga = new SimpleGrantedAuthority(RoleNameConstant.ROLE_NAME_PREFIX + sysRole.getRoleName());
                grantedAuthorities.add(ga);
            }
        }
        return new User(admin.getAccount(), admin.getPassword(), grantedAuthorities);
    }
}
