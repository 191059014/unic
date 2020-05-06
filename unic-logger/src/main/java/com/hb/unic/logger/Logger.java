package com.hb.unic.logger;

import com.alibaba.fastjson.JSON;
import com.hb.unic.logger.model.LoggerEntity;
import com.hb.unic.logger.util.TraceIdUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * ========== 自定义日志 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.Logger.java, v1.0
 * @date 2019年07月16日 11时42分
 */
public class Logger {

    /**
     * org.slf4j.Logger
     */
    private org.slf4j.Logger delegateLogger;

    /**
     * 构造方法
     *
     * @param o 根据此参数来构建日志对象
     */
    Logger(Object o) {
        Objects.requireNonNull(o, "构建日志的参数不能为空");
        if (o instanceof String) {
            delegateLogger = org.slf4j.LoggerFactory.getLogger((String) o);
        } else if (o instanceof Class) {
            delegateLogger = LoggerFactory.getLogger((Class) o);
        } else {
            throw new IllegalArgumentException("构建日志的参数类型有误");
        }
    }

    /**
     * 判断Debug日志级别是否可用
     *
     * @return true为可用
     */
    public boolean isDebugEnabled() {
        return this.delegateLogger.isDebugEnabled();
    }

    /**
     * 判断Info日志级别是否可用
     *
     * @return true为可用
     */
    public boolean isInfoEnabled() {
        return this.delegateLogger.isInfoEnabled();
    }

    /**
     * 判断Warn日志级别是否可用
     *
     * @return true为可用
     */
    public boolean isWarnEnabled() {
        return this.delegateLogger.isWarnEnabled();
    }

    /**
     * 判断Error日志级别是否可用
     *
     * @return true为可用
     */
    public boolean isErrorEnabled() {
        return this.delegateLogger.isErrorEnabled();
    }

    /**
     * debug级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void debug(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        LoggerEntity loggerEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "DEBUG");
        this.delegateLogger.debug(getJsonContent(loggerEntity));
    }

    /**
     * info级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void info(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        LoggerEntity loggerEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "INFO");
        this.delegateLogger.info(getJsonContent(loggerEntity));
    }

    /**
     * warn级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void warn(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        LoggerEntity loggerEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "WARN");
        this.delegateLogger.info(getJsonContent(loggerEntity));
    }

    /**
     * error级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void error(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        LoggerEntity loggerEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "ERROR");
        this.delegateLogger.info(getJsonContent(loggerEntity));
    }

    /**
     * 转换成json
     *
     * @param loggerEntity 日志实体
     * @return 标准json
     */
    private String getJsonContent(LoggerEntity loggerEntity) {
        return JSON.toJSONString(loggerEntity);
    }

    /**
     * 构建日志信息模板
     *
     * @return 标准日志
     */
    private LoggerEntity buildLogTemplate(Object message, StackTraceElement[] stacks, String logLevel) {
        LoggerEntity loggerEntity = new LoggerEntity();
        loggerEntity.setContent(message);
        loggerEntity.setTrace_id(TraceIdUtils.getTraceId());
        loggerEntity.setLog_date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(System.currentTimeMillis())));
        loggerEntity.setLog_level(logLevel);
        loggerEntity.setFile_name(stacks[2].getClassName());
        loggerEntity.setFile_line(stacks[2].getLineNumber());
        return loggerEntity;
    }

    /**
     * 组装日志信息
     *
     * @param format   格式化的字符串
     * @param argArray 参数数组
     * @return FormattingTuple对象
     */
    private Object getLogMsg(String format, Object[] argArray) {
        return MessageFormatter.arrayFormat(format, argArray, null).getMessage();
    }

}
