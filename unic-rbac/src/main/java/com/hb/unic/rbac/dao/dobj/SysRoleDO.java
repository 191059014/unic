package com.hb.unic.rbac.dao.dobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.model.impl.AbstractTenantDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息表数据模型
 *
 * @version v0.1, 2021-09-04 12:48:42, create by Mr.Huang.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRoleDO extends AbstractTenantDO {

    /**
     * 角色名称
     */
    private String roleName;

}
