package com.hb.unic.common.util;

import java.util.List;

/**
 * list工具类
 *
 * @version v0.1, 2021/10/16 18:11, create by huangbiao.
 */
public class ListUtils {

    /**
     * 是否不为空
     *
     * @param list
     *            map
     * @return 结果
     */
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * 是否为空
     *
     * @param list
     *            map
     * @return 结果
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 获取字符串值
     *
     * @param list
     *            集合
     * @param index
     *            键
     * @return 字符串
     */
    public static String getStringValue(List list, Integer index) {
        if (isEmpty(list)) {
            return null;
        }
        Object value = list.get(index);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
