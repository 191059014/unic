package com.hb.unic.rbac.model.dto;

import com.hb.unic.rbac.dao.dobj.SysUserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户缓存信息
 *
 * @version v0.1, 2021/9/19 15:48, create by huangbiao.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCache implements Serializable {

    /**
     * 最后一次登录的ip
     */
    private static final long serialVersionUID = 7327911488607898148L;

    /**
     * 最后一次登录的ip
     */
    private String lastLoginIp;

    /**
     * 用户信息
     */
    private SysUserDO user;

    /**
     * 权限集合
     */
    private Set<String> authorities;

}
