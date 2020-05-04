package com.hb.unic.base.exception;

/**
 * ========== 标准异常类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.exception.StandardRuntimeException.java, v1.0
 * @date 2019年09月05日 14时40分
 */
public class StandardRuntimeException extends RuntimeException {
    /**
     * 错误标识
     */
    private String key;
    /**
     * 错误描述
     */
    private String desc;

    public StandardRuntimeException(String key, String desc) {
        super(key);
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "StandardRuntimeException{" +
                "key='" + key + '\'' +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }

}
