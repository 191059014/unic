package com.hb.unic.logger.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ========== 日志实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.LoggerEntity.java, v1.0
 * @date 2019年07月16日 12时58分
 */
public class LoggerEntity {

    @JSONField(ordinal = 1)
    private Object content;

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

}
