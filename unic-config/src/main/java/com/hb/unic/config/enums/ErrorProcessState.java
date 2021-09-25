package com.hb.unic.config.enums;

import lombok.Getter;

/**
 * 异常看板
 *
 * @version v0.1, 2021/9/23 22:40, create by huangbiao.
 */
@Getter
public enum ErrorProcessState {

    WHTHOUT("whthout", "不需要处理"), PENDING("pending", "待处理"), COMPLETE("complete", "已处理");

    /**
     * 系统名称
     */
    private String value;

    /**
     * 业务类型
     */
    private String name;

    ErrorProcessState(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
