package com.hb.unic.rbac;

import com.hb.unic.cache.Redis;
import com.hb.unic.rbac.common.util.RbacUtils;
import com.hb.unic.rbac.dao.dobj.SysUserDO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * spring security上下文
 *
 * @version v0.1, 2021/9/19 17:46, create by huangbiao.
 */
public class RbacContext {

    /**
     * 获取当前用户id
     * 
     * @return 用户id
     */
    public static long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * 获取当前用户租户ID
     *
     * @return 租户ID
     */
    public static long getCurrentTenantId() {
        return getCurrentUser().getTenantId();
    }

    /**
     * 获取上下文中用户信息
     * 
     * @return 用户信息
     */
    public static SysUserDO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SysUserDO)authentication.getPrincipal();
    }

    /**
     * 清除用户信息
     */
    public static void clear() {
        SysUserDO currentUser = getCurrentUser();
        SecurityContextHolder.clearContext();
        Redis.strOps().delete(RbacUtils.getCurrentUserCacheKey(currentUser.getId()));
    }

}
