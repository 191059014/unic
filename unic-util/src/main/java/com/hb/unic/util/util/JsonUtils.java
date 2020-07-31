package com.hb.unic.util.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        MAPPER
                // 将对象的所有不为空字段全部列入
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                // 取消默认日期格式timestamps
                .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
                // 忽略空Bean转json的错误
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 所有的日期格式统一样式： yyyy-MM-dd HH:mm:ss
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                // 忽略在json字符串中存在，但是在对象中不存在对应属性而抛出异常的情况
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 开启/禁止自动检测
                .enable(MapperFeature.USE_ANNOTATIONS)
                // 是否缩放排列输出
                .enable(SerializationFeature.INDENT_OUTPUT)
                // 将浮点小数转为BigDecimal，防止精度丢失
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                // 将整数转换为long
                .enable(DeserializationFeature.USE_LONG_FOR_INTS)
        ;
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
            LOGGER.info("转换Bean异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * json字符串转换为List<E>
     *
     * @param json           json字符串
     * @param elementClasses list元素泛型类
     * @param <E>            bean类型
     * @return List<E>
     */
    public static <E> List<E> toList(String json, Class<E> elementClasses) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOGGER.info("转换List异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * json字符串转换为Map<String,V>
     *
     * @param json         json字符串
     * @param valueClasses map的值的元素类型
     * @param <V>          值的元素类型
     * @return Map
     */
    public static <V> Map<String, V> toMap(String json, Class<V> valueClasses) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, String.class, valueClasses);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOGGER.info("转换Map异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * 转换成对应的类型
     *
     * @param json         json
     * @param valueTypeRef 类型引用
     * @param <T>          要转换的类型
     * @return 结果
     */
    public static <T> T toType(String json, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(json, valueTypeRef);
        } catch (IOException e) {
            LOGGER.info("转换指定Type异常：{}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

}

    