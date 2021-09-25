package com.hb.unic.rbac.dao.dobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.model.impl.AbstractTenantDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表数据模型
 *
 * @version v0.1, 2021-09-04 12:48:45, create by Mr.Huang.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysUserDO extends AbstractTenantDO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

}
