package com.hb.unic.config.util;

import com.hb.unic.cache.util.CacheUtils;
import org.springframework.util.StringUtils;

/**
 * 工具类
 *
 * @version v0.1, 2021/9/21 17:07, create by huangbiao.
 */
public class ConfigUtils {

    /**
     * 获取缓存key
     * 
     * @param systemName
     *            系统名称
     * @param groupName
     *            分组名称
     * @param configKey
     *            键
     * @return 缓存key
     */
    public static String getGlobalConfigCacheKey(String systemName, String groupName, String configKey) {
        if (StringUtils.isEmpty(configKey)) {
            return CacheUtils.generateRedisKey(systemName, groupName);
        } else {
            return CacheUtils.generateRedisKey(systemName, groupName, configKey);
        }
    }

}
