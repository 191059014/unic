package com.hb.unic.base.exception;

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
    private String message;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }

}
