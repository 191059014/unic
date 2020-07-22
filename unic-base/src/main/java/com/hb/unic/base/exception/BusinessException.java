package com.hb.unic.base.exception;

import com.hb.unic.base.util.ResponseUtils;

/**
 * ========== 业务异常类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.exception.BusinessException.java, v1.0
 * @date 2019年09月05日 14时40分
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误标识
     */
    private String code;
    /**
     * 错误描述
     */
    private String msg;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj 枚举
     */
    public BusinessException(Enum enumObj) {
        this(ResponseUtils.getCode(enumObj), ResponseUtils.getMsg(enumObj));
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    @Override
    public String toString() {
        return "{"
                + "\"code\":\""
                + code + '\"'
                + ",\"msg\":\""
                + msg + '\"'
                + "},\"super-BusinessException\":" + super.toString() + "}";
    }
}
