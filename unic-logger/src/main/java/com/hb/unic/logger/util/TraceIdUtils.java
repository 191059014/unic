package com.hb.unic.logger.util;

import com.hb.unic.logger.common.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * ========== traceId工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.TraceIdUtils.java, v1.0
 * @date 2019年07月16日 10时09分
 */
public class TraceIdUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdUtils.class);

    /**
     * 存放trace_id的容器
     */
    private static final ThreadLocal<String> TRACE_ID = new InheritableThreadLocal();

    /**
     * 获取trace_id
     *
     * @return trace_id
     */
    public static String getTraceId() {
        String traceId = TRACE_ID.get();
        if (traceId == null) {
            traceId = generateTraceId() + Consts.TRACEID_DEFAULT_SUFFIX;
            setTraceId(traceId);
            LOGGER.info("traceId is null，set default：{}", traceId);
        }
        return traceId;
    }

    /**
     * 设置trace_id
     *
     * @param traceId trace_id
     */
    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    /**
     * 删除trace_id
     */
    public static void removeTraceId() {
        TRACE_ID.remove();
    }

    /**
     * 生成新的traceId
     *
     * @return 字符串
     */
    public static String generateTraceId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

}
