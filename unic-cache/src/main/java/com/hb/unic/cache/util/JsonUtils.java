package com.hb.unic.cache.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;

/**
 * json工具类
 *
 * @version v0.1, 2020/7/21 17:35, create by huangbiao.
 */
public class JsonUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * ObjectMapper对象
     */
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.info("转换json异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

}

    