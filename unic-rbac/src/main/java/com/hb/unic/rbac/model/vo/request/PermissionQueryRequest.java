package com.hb.unic.rbac.model.vo.request;

import lombok.Data;

/**
 * 权限查询参数
 *
 * @version v0.1, 2021/9/14 21:55, create by huangbiao.
 */
@Data
public class PermissionQueryRequest {

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 资源类型：folder-目录，page-页面，button-按钮
     */
    private String resourceType;

    /**
     * 商户ID
     */
    private Long tenantId;

}
