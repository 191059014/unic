package com.hb.unic.base.exception;

import com.hb.unic.util.util.EnumUtils;

/**
 * 公共业务异常类
 *
 * @version v0.1, 2021/1/27 13:36, create by huangbiao.
 */
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

    public BusinessException(String key, String message) {
        this(key, message, null);
    }

    public BusinessException(String key, String message, Object param) {
        super(key);
        this.key = key;
        this.msg = message;
        this.param = param;
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj
     *            枚举
     */
    public BusinessException(Enum enumObj) {
        this(enumObj, null);
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj
     *            枚举
     * @param param
     *            需要携带的参数
     */
    public BusinessException(Enum enumObj, Object param) {
        super(String.valueOf(EnumUtils.get(enumObj, EnumUtils.KeysEnum.code.name())));
        this.key = String.valueOf(EnumUtils.get(enumObj, EnumUtils.KeysEnum.code.name()));
        this.msg = String.valueOf(EnumUtils.get(enumObj, EnumUtils.KeysEnum.msg.name()));
        this.param = param;
    }

    public String getKey() {
        return key;
    }

    public String getMsg() {
        return msg;
    }

    public Object getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "BusinessException(" + "key='" + key + '\'' + ", msg='" + msg + '\'' + ", param=" + param + ")";
    }

}
