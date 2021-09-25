package com.hb.unic.rbac.common.enums;

/**
 * 资源类型
 *
 * @version v0.1, 2020/7/29 10:58, create by huangbiao.
 */
public enum ResourceType {

    FOLDER("folder", "目录"),
    PAGE("page", "页面"),
    BUTTON("button", "按钮");

    // 值
    private String value;

    // 名称
    private String name;

    ResourceType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}

    