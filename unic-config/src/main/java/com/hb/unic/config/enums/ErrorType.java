package com.hb.unic.config.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常看板
 *
 * @version v0.1, 2021/9/23 22:40, create by huangbiao.
 */
@Getter
public enum ErrorType {

    BASE_SYSTEM_ERR("hbbase", "基础平台", "base_system_exception", "基础平台系统异常"),
    RBAC_SYSTEM_ERR("hbrbac", "用户平台", "rbac_system_exception", "用户平台系统异常"),
    WEB_SYSTEM_ERR("bizweb", "管理平台", "web_system_exception", "管理平台系统异常");

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统名称描述
     */
    private String systemNameDesc;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 业务类型描述
     */
    private String bizTypeDesc;

    ErrorType(String systemName, String systemNameDesc, String bizType, String bizTypeDesc) {
        this.systemName = systemName;
        this.systemNameDesc = systemNameDesc;
        this.bizType = bizType;
        this.bizTypeDesc = bizTypeDesc;
    }

    /**
     * 通过系统名称查询
     *
     * @param systemName
     *            系统名称
     * @return 结果
     */
    public static List<ErrorType> getBySystemName(String systemName) {
        return Arrays.stream(ErrorType.values()).filter(err -> err.getSystemName().equals(systemName))
            .collect(Collectors.toList());
    }

}
