package com.hb.unic.base.exception;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.apache.commons.lang3.StringUtils;


/**
 * ========== 标准异常类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.exception.StandardRuntimeException.java, v1.0
 * @date 2019年09月05日 14时40分
 */
public class StandardRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1237220913573671015L;

    protected Logger logger = LoggerFactory.getLogger(StandardRuntimeException.class);
    private String key;
    private String stack;
    private Object[] paras;

    public StandardRuntimeException(String key) {
        this.processKey(key, (Throwable)null);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey());
        }

    }

    public StandardRuntimeException(String key, Object... paras) {
        this.processKey(key, (Throwable)null);
        this.paras = paras;
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey());
        }

    }

    public StandardRuntimeException(String key, String message, Object... paras) {
        super(message);
        this.processKey(key, (Throwable)null);
        this.paras = paras;
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey());
        }

    }

    public StandardRuntimeException(Throwable e, Object... paras) {
        super(e);
        this.processKey((String)null, e);
        this.paras = paras;
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

    }

    public StandardRuntimeException(String key, Throwable e, Object... paras) {
        super(e);
        this.processKey(key, e);
        this.paras = paras;
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

    }

    public StandardRuntimeException(String key, String message, Throwable e, Object... paras) {
        super(message, e);
        this.processKey(key, e);
        this.paras = paras;
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

    }

    public StandardRuntimeException() {
        this.processKey((String)null, (Throwable)null);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey());
        }

    }

    public StandardRuntimeException(String key, String message) {
        super(message);
        this.processKey(key, (Throwable)null);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey());
        }

    }

    public StandardRuntimeException(Throwable e) {
        super(e);
        this.processKey((String)null, e);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

    }

    public StandardRuntimeException(String key, Throwable e) {
        super(e);
        this.processKey(key, e);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

    }

    public StandardRuntimeException(String key, String message, Throwable e) {
        super(message, e);
        this.processKey(key, e);
        if (this.logger.isErrorEnabled()) {
            this.logger.error(this.getKey(), e);
        }

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

    private void processKey(String key, Throwable e) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int pos = 3;
        if (StringUtils.isNotBlank(key)) {
            this.key = key;
        } else if (e != null) {
            this.key = e.getClass().getSimpleName();
        }

        if (ste != null && ste.length > pos) {
            String clzName = ste[pos].getClassName();
            String methodName = ste[pos].getMethodName();
            this.stack = clzName + "." + methodName;
        }

    }

}
