package com.hb.unic.cache.util;

/**
 * 工具类
 *
 * @version v0.1, 2021/9/25 16:40, create by huangbiao.
 */
public class CacheUtils {

    /**
     * 生成redis缓存键
     *
     * @param arr
     *            一些需要拼接的参数
     * @return 缓存key
     */
    public static String generateRedisKey(String... arr) {
        return String.join(":", arr);
    }

}
