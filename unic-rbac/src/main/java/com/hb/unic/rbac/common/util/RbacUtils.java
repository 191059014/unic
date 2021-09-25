package com.hb.unic.rbac.common.util;

import com.hb.unic.cache.util.CacheUtils;
import com.hb.unic.rbac.common.constant.RbacConsts;

/**
 * 工具类
 *
 * @version v0.1, 2021/9/4 22:44, create by huangbiao.
 */
public class RbacUtils {

    /**
     * 判断是否是超级管理员
     * 
     * @param userId
     *            用户ID
     * @return 结果
     */
    public static boolean isSuperAdmin(long userId) {
        return 0L == userId;
    }

    /**
     * 获取当前登录用户的缓存key
     * 
     * @param userId
     *            用户id
     * @return 缓存key
     */
    public static String getCurrentUserCacheKey(Object userId) {
        return CacheUtils.generateRedisKey(RbacConsts.PROJECT_NAME, "loginUser", userId.toString());
    }

}
