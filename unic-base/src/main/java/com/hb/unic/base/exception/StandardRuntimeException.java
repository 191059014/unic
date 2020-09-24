package com.hb.unic.base.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ========== 标准的运行时异常 ==========
 *
 * @author Mr.huang
 * @version v1.0，2019年09月05日 14时40分
 */
public class StandardRuntimeException extends RuntimeException {
    /**
     * 序列化
     */
    private static final long serialVersionUID = -8027145634517186487L;
    /**
     * 附带参数
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StandardRuntimeException.class);
    /**
     * 错误标识
     */
    private String key;
    /**
     * 简单的堆栈
     */
    private String stack;
    /**
     * 附带参数
     */
    private Object[] paras;

    public StandardRuntimeException() {
        processKey(null, null);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey());
    }

    public StandardRuntimeException(Throwable e) {
        super(e);
        processKey(null, e);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public StandardRuntimeException(String key) {
        processKey(key, null);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey());
    }

    public StandardRuntimeException(String key, String message) {
        super(message);
        processKey(key, null);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey());
    }

    public StandardRuntimeException(String key, Throwable e) {
        super(e);
        processKey(key, e);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public StandardRuntimeException(String key, Object[] paras) {
        processKey(key, null);
        this.paras = paras;
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey());
    }

    public StandardRuntimeException(Throwable e, Object[] paras) {
        super(e);
        processKey(null, e);
        this.paras = paras;
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public StandardRuntimeException(String key, String message, Object[] paras) {
        super(message);
        processKey(key, null);
        this.paras = paras;
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey());
    }

    public StandardRuntimeException(String key, Throwable e, Object[] paras) {
        super(e);
        processKey(key, e);
        this.paras = paras;
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public StandardRuntimeException(String key, String message, Throwable e) {
        super(message, e);
        processKey(key, e);
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public StandardRuntimeException(String key, String message, Throwable e, Object[] paras) {
        super(message, e);
        processKey(key, e);
        this.paras = paras;
        if (this.LOGGER.isErrorEnabled())
            this.LOGGER.error(getKey(), e);
    }

    public String getKey() {
        return this.key;
    }

    public Object[] getParas() {
        return this.paras;
    }

    public String getStack() {
        return this.stack;
    }

    /**
     * 初始化key和stack信息
     *
     * @param key 异常key
     * @param e   异常
     */
    private void processKey(String key, Throwable e) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int pos = 3;
        if (StringUtils.isNotBlank(key)) {
            this.key = key;
        } else if (e != null) {
            this.key = e.getClass().getSimpleName();
        }
        if ((ste != null) && (ste.length > pos)) {
            String clzName = ste[pos].getClassName();
            String methodName = ste[pos].getMethodName();
            this.stack = (clzName + "." + methodName);
        }
    }
}
