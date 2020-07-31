package com.hb.unic.base.exception;

import com.hb.unic.base.util.ResponseUtils;

import java.util.Arrays;

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
    private String key;
    /**
     * 附带参数
     */
    private Object[] paras;

    public BusinessException(String key, Object... paras) {
        this.key = key;
        this.paras = paras;
    }

    public BusinessException(String key, String message, Object... paras) {
        super(message);
        this.key = key;
        this.paras = paras;
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj 枚举
     */
    public BusinessException(Enum enumObj) {
        this(ResponseUtils.getCode(enumObj), ResponseUtils.getMsg(enumObj));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object[] getParas() {
        return paras;
    }

    public void setParas(Object[] paras) {
        this.paras = paras;
    }

    @Override
    public String toString() {
        return "{"
                + "\"key\":\""
                + key + '\"'
                + ",\"paras\":"
                + Arrays.toString(paras)
                + "},\"super-BusinessException\":" + super.toString() + "}";
    }
}
