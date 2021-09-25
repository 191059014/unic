package com.hb.unic.common.standard;

import lombok.Getter;

/**
 * 公共业务异常类
 *
 * @version v0.1, 2021/1/27 13:36, create by huangbiao.
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误标识
     */
    private String key;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 附带参数
     */
    private Object param;

    public BusinessException(String key, String msg) {
        this(key, msg, null);
    }

    public BusinessException(String key, String msg, Object param) {
        super(String.join("_", key, msg));
        this.key = key;
        this.msg = msg;
        this.param = param;
    }

    public BusinessException(IErrorCode errorCode) {
        this(errorCode, null);
    }

    public BusinessException(IErrorCode errorCode, Object param) {
        super(String.join("_", errorCode.getCode(), errorCode.getMsg()));
        this.key = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.param = param;
    }

}
