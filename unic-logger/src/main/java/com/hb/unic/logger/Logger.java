package com.hb.unic.logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        if (o == null) {
            throw new IllegalArgumentException("构建日志的参数不能为空");
        }
        if (o instanceof String) {
            delegateLogger = org.slf4j.LoggerFactory.getLogger((String) o);
        } else if (o instanceof Class) {
            delegateLogger = LoggerFactory.getLogger((Class) o);
        } else {
            throw new IllegalArgumentException("构建日志的参数类型有误");
        }
    }

    /**
     * info级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void info(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        CommonLogEntity commonLogEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "INFO");
        this.delegateLogger.info(getJsonContent(commonLogEntity));
    }

    /**
     * error级别的日志打印
     *
     * @param format    格式化的日志信息
     * @param arguments 参数
     */
    public void error(String format, Object... arguments) {
        Object message = this.getLogMsg(format, arguments);
        CommonLogEntity commonLogEntity = this.buildLogTemplate(message, Thread.currentThread().getStackTrace(), "ERROR");
        this.delegateLogger.info(getJsonContent(commonLogEntity));
    }

    /**
     * 转换成json
     *
     * @param commonLogEntity 日志实体
     * @return 标准json
     */
    private String getJsonContent(CommonLogEntity commonLogEntity) {
        return JSON.toJSONString(commonLogEntity);
    }

    /**
     * 构建日志信息模板
     *
     * @return 标准日志
     */
    private CommonLogEntity buildLogTemplate(Object message, StackTraceElement[] stacks, String logLevel) {
        CommonLogEntity commonLogEntity = new CommonLogEntity();
        commonLogEntity.setContent(message);
        commonLogEntity.setTrace_id(TraceIdUtils.getTraceId());
        commonLogEntity.setLog_date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(System.currentTimeMillis())));
        commonLogEntity.setLog_level(logLevel);
        commonLogEntity.setFile_name(stacks[2].getClassName());
        commonLogEntity.setFile_line(stacks[2].getLineNumber());
        return commonLogEntity;
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

    /**
     * 标准日志实体类
     *
     * @param <T>
     */
    private class CommonLogEntity<T> {

        @JSONField(ordinal = 1)
        private T content;
        @JSONField(ordinal = 2)
        private String trace_id;
        @JSONField(ordinal = 3)
        private String log_date;
        @JSONField(ordinal = 4)
        private String log_level;
        @JSONField(ordinal = 5)
        private String file_name;
        @JSONField(ordinal = 6)
        private Integer file_line;

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public String getTrace_id() {
            return trace_id;
        }

        public void setTrace_id(String trace_id) {
            this.trace_id = trace_id;
        }

        public String getLog_date() {
            return log_date;
        }

        public void setLog_date(String log_date) {
            this.log_date = log_date;
        }

        public String getLog_level() {
            return log_level;
        }

        public void setLog_level(String log_level) {
            this.log_level = log_level;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public Integer getFile_line() {
            return file_line;
        }

        public void setFile_line(Integer file_line) {
            this.file_line = file_line;
        }
    }

}
