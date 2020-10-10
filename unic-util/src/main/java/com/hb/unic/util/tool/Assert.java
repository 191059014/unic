package com.hb.unic.util.tool;

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
     * 不为空-Object，否则抛出IllegalArgumentException异常
     *
     * @param obj     对象
     * @param message 错误信息
     */
    public static void notNull(Object obj, String message) {
        ifTrueThrows(obj == null, message);
    }

    /**
     * 不为空-字符串，否则抛出IllegalArgumentException异常
     *
     * @param str     字符串
     * @param message 错误信息
     */
    public static void hasText(String str, String message) {
        ifTrueThrows(str == null || "".equals(str), message);
    }

    /**
     * 不为空-Map，否则抛出IllegalArgumentException异常
     *
     * @param map     map集合
     * @param message 错误信息
     */
    public static <T extends Map> void notEmpty(T map, String message) {
        ifTrueThrows(map == null || map.isEmpty(), message);
    }

    /**
     * 不为空-Collection，否则抛出IllegalArgumentException异常
     *
     * @param collection collection集合
     * @param message    错误信息
     */
    public static <T extends Collection> void notEmpty(T collection, String message) {
        ifTrueThrows(collection == null || collection.isEmpty(), message);
    }

    /**
     * 表达式为false则抛出IllegalArgumentException异常
     *
     * @param assertTrue 表达式
     * @param message    错误信息
     */
    public static void ifTrueThrows(boolean assertTrue, String message) {
        if (assertTrue) {
            throw new IllegalArgumentException(message);
        }
    }

}

    