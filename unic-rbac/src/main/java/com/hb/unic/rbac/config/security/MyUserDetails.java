package com.hb.unic.rbac.config.security;

import com.hb.unic.rbac.dao.dobj.SysUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义用户信息
 *
 * @version v0.1, 2021/9/18 22:12, create by huangbiao.
 */
public class MyUserDetails implements UserDetails {

    /**
     * 用户信息
     */
    private SysUserDO user;

    /**
     * 权限集合
     */
    private Set<String> authorities;

    public MyUserDetails(SysUserDO user, Set<String> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(authorities)) {
            authorities = new HashSet<>();
        } else {
            authorities.forEach(permission -> list.add(new SimpleGrantedAuthority(permission)));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SysUserDO getUser() {
        return user;
    }

    public void setUser(SysUserDO user) {
        this.user = user;
    }

}
