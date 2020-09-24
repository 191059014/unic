package com.hb.unic.util.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Object工具类
 *
 * @version v0.1, 2020/7/31 16:04, create by huangbiao.
 */
public class ObjectUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 将多个Object元素转换为Object数组
     *
     * @param objs 多个Object元素
     * @return Object数组
     */
    public static Object[] toArray(Object... objs) {
        return objs;
    }

    /**
     * 将Object转换为String
     *
     * @param value 待转换的值
     * @return String
     */
    public static String toString(Object value) {
        return value == null ? null : value.toString();
    }

    /**
     * 将Object转换为Integer
     *
     * @param value 待转换的值
     * @return Integer
     */
    public static Integer toInteger(Object value) {
        return value == null ? null : Integer.valueOf(value.toString());
    }

    /**
     * 将Object转换为Long
     *
     * @param value 待转换的值
     * @return Long
     */
    public static Long toLong(Object value) {
        return value == null ? null : Long.valueOf(value.toString());
    }

    /**
     * 将Object转换为Long
     *
     * @param value 待转换的值
     * @return BigDecimal
     */
    public static BigDecimal toBigDecimal(Object value) {
        return value == null ? BigDecimal.ZERO : new BigDecimal(value.toString());
    }

    /**
     * 将Object转换为Boolean
     *
     * @param value 待转换的值
     * @return Boolean
     */
    public static Boolean toBoolean(Object value) {
        return value == null ? Boolean.FALSE : Boolean.valueOf(value.toString());
    }

    /**
     * 通过完整类名实例化对象
     *
     * @param className 完整类名
     * @return 对象
     */
    public static Object newInstance(String className) {
        if (StringUtils.isNotBlank(className)) {
            Class clz = null;
            try {
                clz = Class.forName(className);
                if (clz != null) {
                    return clz.newInstance();
                }
            } catch (Exception e) {
                LOGGER.error("newInstance error=", e);
            }
        }
        return null;
    }

    /**
     * 是否全为null
     *
     * @param objArr 数组
     * @return true-全为null
     */
    public static boolean isAllNull(Object... objArr) {
        if (objArr == null)
            return true;
        for (Object obj : objArr)
            if (obj != null)
                return false;
        return true;
    }

    /**
     * 最低有一个不为null
     *
     * @param objArr 数组
     * @return true-最低有一个不为null
     */
    public static boolean isAnyNotNull(Object... objArr) {
        return !isAllNull(objArr);
    }

    /**
     * 最低有一个为null
     *
     * @param objArr 数组
     * @return true-最低有一个为null
     */
    public static boolean isAnyNull(Object... objArr) {
        return !isAllNotNull(objArr);
    }

    /**
     * 全否全不为null
     *
     * @param objArr 数组
     * @return true-全不为null
     */
    public static boolean isAllNotNull(Object... objArr) {
        if (objArr == null)
            return false;
        for (Object obj : objArr)
            if (obj == null)
                return false;
        return true;
    }

}

    