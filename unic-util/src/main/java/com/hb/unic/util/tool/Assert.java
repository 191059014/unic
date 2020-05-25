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
     * 不为空-Object
     *
     * @param obj     对象
     * @param message 错误信息
     */
    public static void assertNotNull(Object obj, String message) {
        assertTrueThrows(obj == null, message);
    }

    /**
     * 不为空-字符串
     *
     * @param str     字符串
     * @param message 错误信息
     */
    public static void assertNotEmpty(String str, String message) {
        assertTrueThrows(str == null || "".equals(str), message);
    }

    /**
     * 不为空-Map
     *
     * @param map     map集合
     * @param message 错误信息
     */
    public static <T extends Map> void assertNotEmpty(T map, String message) {
        assertTrueThrows(map == null || map.isEmpty(), message);
    }

    /**
     * 不为空-Collection
     *
     * @param collection collection集合
     * @param message    错误信息
     */
    public static <T extends Collection> void assertNotEmpty(T collection, String message) {
        assertTrueThrows(collection == null || collection.isEmpty(), message);
    }

    /**
     * 表达式为false则抛出IllegalArgumentException异常
     *
     * @param assertTrue 表达式
     * @param message    错误信息
     */
    public static void assertTrueThrows(boolean assertTrue, String message) {
        if (assertTrue) {
            throw new IllegalArgumentException(message);
        }
    }

}

    