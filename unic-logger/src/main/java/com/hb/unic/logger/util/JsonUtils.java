package com.hb.unic.logger.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

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

    /**
     * 静态代码块，初始化ObjectMapper
     */
    static {
        initMapper(MAPPER);
    }

    /**
     * 初始化ObjectMapper
     */
    private static void initMapper(ObjectMapper mapper) {
        mapper
                // 将对象的所有不为空字段全部列入
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // 取消默认日期格式timestamps
                .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
                // 忽略空Bean转json的错误
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 所有的日期格式统一样式： yyyy-MM-dd HH:mm:ss
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                // 忽略在json字符串中存在，但是在对象中不存在对应属性而抛出异常的情况
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 开启/禁止自动检测
                .enable(MapperFeature.USE_ANNOTATIONS);
    }

    /**
     * 转换为json
     *
     * @param o 任意对象，javabean，list，map，其他复杂对象
     * @return json字符串
     */
    public static String toJson(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.info("转换json异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

}

    