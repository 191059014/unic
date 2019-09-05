package com.hb.unic.logger;

/**
 * ========== 自定义日志 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.Logger.java, v1.0
 * @date 2019年07月16日 11时42分
 */
public class Logger {

    /**
     * slf4j Logger
     */
    private org.slf4j.Logger delegateLogger;

    /**
     * ########## 构造方法，根据参数类型判断具体要用的Logger对象 ##########
     */
    Logger(Object param) {
        if (param instanceof Class) {
            delegateLogger = org.slf4j.LoggerFactory.getLogger((Class) param);
        } else if (param instanceof String) {
            delegateLogger = org.slf4j.LoggerFactory.getLogger((String) param);
        }
    }

    /**
     * ########## 判断debug日志级别是否可用 ##########
     *
     * @return true-可用，false-不可用
     */
    public boolean isDebugEnabled() {
        return this.delegateLogger.isDebugEnabled();
    }

    /**
     * ########## debug日志 ##########
     *
     * @param msg    日志信息
     * @param params 参数
     */
    public void debug(String msg, Object... params) {
        this.delegateLogger.debug(getLoggerEntity(msg), params);
    }

    /**
     * ########## 判断info日志级别是否可用 ##########
     *
     * @return true-可用，false-不可用
     */
    public boolean isInfoEnabled() {
        return this.delegateLogger.isInfoEnabled();
    }

    /**
     * ########## info日志 ##########
     *
     * @param msg    日志信息
     * @param params 参数
     */
    public void info(String msg, Object... params) {
        this.delegateLogger.info(getLoggerEntity(msg), params);
    }

    /**
     * ########## 判断warn日志级别是否可用 ##########
     *
     * @return true-可用，false-不可用
     */
    public boolean isWarnEnabled() {
        return this.delegateLogger.isWarnEnabled();
    }

    /**
     * ########## warn日志 ##########
     *
     * @param msg    日志信息
     * @param params 参数
     */
    public void warn(String msg, Object... params) {
        this.delegateLogger.warn(getLoggerEntity(msg), params);
    }

    /**
     * ########## 判断error日志级别是否可用 ##########
     *
     * @return true-可用，false-不可用
     */
    public boolean isErrorEnabled() {
        return this.delegateLogger.isErrorEnabled();
    }

    /**
     * ########## error日志 ##########
     *
     * @param msg    日志信息
     * @param params 参数
     */
    public void error(String msg, Object... params) {
        this.delegateLogger.error(getLoggerEntity(msg), params);
    }

    /**
     * ########## 获取日志信息 ##########
     *
     * @param msg 日志信息
     * @return 包装后的日志信息
     */
    private String getLoggerEntity(String msg) {
        String traceId = TraceIdUtils.getTraceId();
        if (traceId == null || traceId == "") {
            return msg;
        }
        LoggerEntity loggerEntity = new LoggerEntity();
        loggerEntity.setTraceId(traceId);
        loggerEntity.setContent(msg);
        return loggerEntity.toString();
    }

}
