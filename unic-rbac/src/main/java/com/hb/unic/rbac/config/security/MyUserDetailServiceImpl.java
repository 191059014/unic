package com.hb.unic.rbac.config.security;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import com.hb.unic.rbac.dao.dobj.SysUserDO;
import com.hb.unic.rbac.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 登录认证
 *
 * @version v0.1, 2021/9/18 22:10, create by huangbiao.
 */
@Component("myUserDetailsService")
@Slf4j
public class MyUserDetailServiceImpl implements UserDetailsService {

    /**
     * 用户信息表服务层
     */
    @Resource
    private ISysUserService sysUserService;

    @Override
    @InOutLog("登录认证")
    public UserDetails loadUserByUsername(String usernameOrMobile) throws UsernameNotFoundException {
        SysUserDO sysUser = sysUserService.findByUsernameOrMobile(usernameOrMobile);
        if (sysUser == null) {
            throw new UsernameNotFoundException(RbacResultCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        Set<String> permissions = sysUserService.findPermissions(sysUser.getId());
        return new MyUserDetails(sysUser, permissions);
    }

}
