package com.hb.unic.logger.model;

/**
 * ========== 日志实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.LoggerEntity.java, v1.0
 * @date 2019年07月16日 12时58分
 */
public class LoggerEntity {

    // 日志摘要信息
    private Object content;
    // 链路追踪trace_id
    private String trace_id;
    // 日期
    private String log_date;
    // 级别
    private String log_level;
    // 文件名称
    private String file_name;
    // 行数
    private Integer file_line;
    // 主机名
    private String host_name;
    // 线程名称
    private String thread_name;
    // ip地址
    private String ip;
    // 应用名
    private String app_name;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
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

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getThread_name() {
        return thread_name;
    }

    public void setThread_name(String thread_name) {
        this.thread_name = thread_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    @Override
    public String toString() {
        return "{"
                + "\"content\":"
                + content
                + ",\"trace_id\":\""
                + trace_id + '\"'
                + ",\"log_date\":\""
                + log_date + '\"'
                + ",\"log_level\":\""
                + log_level + '\"'
                + ",\"file_name\":\""
                + file_name + '\"'
                + ",\"file_line\":"
                + file_line
                + ",\"host_name\":\""
                + host_name + '\"'
                + ",\"thread_name\":\""
                + thread_name + '\"'
                + ",\"ip\":\""
                + ip + '\"'
                + ",\"app_name\":\""
                + app_name + '\"'
                + "}";
    }
}
