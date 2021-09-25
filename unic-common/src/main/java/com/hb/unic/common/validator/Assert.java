package com.hb.unic.common.validator;

import com.hb.unic.common.standard.BusinessException;
import com.hb.unic.common.standard.IErrorCode;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义断言
 *
 * @author Mr.Huang
 * @version v0.1, Assert.java, 2020/5/25 15:52, create by huangbiao.
 */
public class Assert {

    /**
     * 不为空-Object，否则抛出异常
     *
     * @param obj
     *            对象
     * @param errorCode
     *            错误信息
     */
    public static void notNull(Object obj, IErrorCode errorCode) {
        ifTrueThrows(obj == null, errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 不为空-Object，否则抛出异常
     *
     * @param obj
     *            对象
     */
    public static void notNull(Object obj, String msg) {
        ifTrueThrows(obj == null, msg);
    }

    /**
     * 不为空-字符串，否则抛出异常
     *
     * @param str
     *            字符串
     * @param errorCode
     *            错误信息
     */
    public static void hasText(String str, IErrorCode errorCode) {
        ifTrueThrows(str == null || "".equals(str), errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 不为空-字符串，否则抛出异常
     *
     * @param str
     *            字符串
     */
    public static void hasText(String str, String msg) {
        ifTrueThrows(str == null || "".equals(str), msg);
    }

    /**
     * 不为空-Map，否则抛出异常
     *
     * @param map
     *            map集合
     * @param errorCode
     *            错误信息
     */
    public static <T extends Map> void notEmpty(T map, IErrorCode errorCode) {
        ifTrueThrows(map == null || map.isEmpty(), errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 不为空-Map，否则抛出异常
     *
     * @param map
     *            map集合
     */
    public static <T extends Map> void notEmpty(T map, String msg) {
        ifTrueThrows(map == null || map.isEmpty(), msg);
    }

    /**
     * 不为空-Collection，否则抛出异常
     *
     * @param collection
     *            collection集合
     * @param errorCode
     *            错误信息
     */
    public static <T extends Collection> void notEmpty(T collection, IErrorCode errorCode) {
        ifTrueThrows(collection == null || collection.isEmpty(), errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 不为空-Collection，否则抛出异常
     *
     * @param collection
     *            collection集合
     */
    public static <T extends Collection> void notEmpty(T collection, String msg) {
        ifTrueThrows(collection == null || collection.isEmpty(), msg);
    }

    /**
     * 表达式为false则抛出异常
     *
     * @param assertTrue
     *            表达式
     * @param key
     *            错误码
     * @param msg
     *            错误信息
     */
    public static void ifTrueThrows(boolean assertTrue, String key, String msg) {
        if (assertTrue) {
            throw new BusinessException(key, msg);
        }
    }

    /**
     * 表达式为false则抛出异常
     *
     * @param assertTrue
     *            表达式
     * @param errorCode
     *            错误码
     */
    public static void ifTrueThrows(boolean assertTrue, IErrorCode errorCode) {
        ifTrueThrows(assertTrue, errorCode.getCode(), errorCode.getMsg());
    }

    /**
     * 表达式为false则抛出异常
     *
     * @param assertTrue
     *            表达式
     * @param msg
     *            错误信息
     */
    public static void ifTrueThrows(boolean assertTrue, String msg) {
        if (assertTrue) {
            throw new IllegalArgumentException(msg);
        }
    }

}
