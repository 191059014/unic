package com.hb.unic.logger;

/**
 * ========== 日志实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.LoggerEntity.java, v1.0
 * @date 2019年07月16日 12时58分
 */
public class LoggerEntity {

    private String traceId;

    private Object content;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{traceId:\"" + traceId + "\",content:\"" + String.valueOf(content) + "\"}";
    }
}
