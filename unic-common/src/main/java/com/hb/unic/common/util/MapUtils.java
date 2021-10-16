package com.hb.unic.common.util;

import java.util.Arrays;
import java.util.Map;

/**
 * map工具类
 *
 * @version v0.1, 2021/10/16 17:47, create by huangbiao.
 */
public class MapUtils {

    /**
     * 是否不为空
     *
     * @param map
     *            map
     * @return 结果
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 是否为空
     * 
     * @param map
     *            map
     * @return 结果
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 通过key模糊匹配，获取value
     * 
     * @param map
     *            集合
     * @param keyParts
     *            key的一部分
     * @param <V>
     *            value的类型
     * @return 结果
     */
    public static <V> V getValueByKeyLike(Map<String, V> map, String... keyParts) {
        if (isEmpty(map) || keyParts == null || keyParts.length == 0) {
            return null;
        }
        for (Map.Entry<String, V> entry : map.entrySet()) {
            String key = entry.getKey();
            V value = entry.getValue();
            boolean allMatch = Arrays.stream(keyParts).allMatch(key::contains);
            if (allMatch) {
                return value;
            }
        }
        return null;
    }

    /**
     * 获取字符串值
     *
     * @param map
     *            集合
     * @param key
     *            键
     * @return 字符串
     */
    public static String getStringValue(Map map, String key) {
        if (isEmpty(map) || key == null) {
            return null;
        }
        Object value = map.get(key);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
