package com.hb.unic.util.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;

import java.io.IOException;
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
     * ObjectMapper对象，针对list
     */
    private final static ObjectMapper LIST_MAPPER = new ObjectMapper();

    /**
     * ObjectMapper对象，针对map
     */
    private final static ObjectMapper MAP_MAPPER = new ObjectMapper();

    /**
     * 静态代码块，初始化ObjectMapper
     */
    static {
        initMapper(MAPPER);
        initMapper(LIST_MAPPER);
        initMapper(MAP_MAPPER);
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

    /**
     * 转换为格式化后的json
     *
     * @param o 任意对象，javabean，list，map，其他复杂对象
     * @return json字符串
     */
    public static String toJsonPretty(Object o) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            LOGGER.info("转换格式化json异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * json字符串转换为bean
     *
     * @param json  json字符串
     * @param clazz bean对应的类
     * @param <T>   bean类型
     * @return bean
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.info("转换Object异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * json字符串转换为bean
     *
     * @param json  json字符串
     * @param clazz bean对应的类
     * @param <T>   bean类型
     * @return bean
     */
    public static <T> T toList(String json,  Class<?> collectionClass, Class<?>... elementClasses) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.info("转换Object异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}

    