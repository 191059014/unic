package com.hb.unic.base.exception;

import com.hb.unic.base.util.ResponseUtils;

/**
 * ========== 业务异常类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.exception.BusinessException.java, v1.0
 * @date 2019年09月05日 14时40分
 */
public class BusinessException extends StandardRuntimeException {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -7609091570577553080L;

    public BusinessException(String key, String message) {
        super(key, message);
    }

    public BusinessException(String key, Object... paras) {
        super(key, paras);
    }

    public BusinessException(String key, String message, Object... paras) {
        super(key, message, paras);
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj 枚举
     */
    public BusinessException(Enum enumObj) {
        this(ResponseUtils.getCode(enumObj), ResponseUtils.getMsg(enumObj));
    }

    /**
     * 枚举类，取code和msg字段
     *
     * @param enumObj 枚举
     * @param paras   需要携带的参数
     */
    public BusinessException(Enum enumObj, Object... paras) {
        this(ResponseUtils.getCode(enumObj), ResponseUtils.getMsg(enumObj), paras);
    }


}
