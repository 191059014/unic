package com.hb.unic.rbac.dao.dobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.model.impl.AbstractBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限信息表数据模型
 *
 * @version v0.1, 2021-09-04 12:48:40, create by Mr.Huang.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysPermissionDO extends AbstractBaseDO {

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 资源类型：folder-目录，page-页面，button-按钮
     */
    private String resourceType;

    /**
     * 权限值
     */
    private String permissionValue;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String url;

    /**
     * 父级id
     */
    private Long parentId;

}
