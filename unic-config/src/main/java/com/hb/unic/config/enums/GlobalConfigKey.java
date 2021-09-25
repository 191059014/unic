package com.hb.unic.config.enums;

import lombok.Getter;

/**
 * 全局配置key
 *
 * @version v0.1, 2021/9/21 17:56, create by huangbiao.
 */
@Getter
public enum GlobalConfigKey {

    BLACK_LIST("hbbase", "blacklist", "ipArr");

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 配置标识
     */
    private String configKey;

    GlobalConfigKey(String systemName, String groupName, String configKey) {
        this.systemName = systemName;
        this.groupName = groupName;
        this.configKey = configKey;
    }

}
