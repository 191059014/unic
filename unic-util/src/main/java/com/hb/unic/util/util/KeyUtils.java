package com.hb.unic.util.util;

/**
 * 生成唯一key工具类
 *
 * @version v0.1, 2020/9/1 16:17, create by huangbiao.
 */
public class KeyUtils {

    /**
     * 生成唯一key
     *
     * @param pkFlag key标识
     * @return 标识+yyMMddHHmmssSSS（15位）+随机数（6位）
     */
    public static String getUniqueKey(String pkFlag) {
        return new StringBuilder(pkFlag).append(DateUtils.getNowTimeStr(DateUtils.FORMAT_12)).append(RandomUtils.getRandomOfLength(6)).toString();
    }

    /**
     * 获取多租户ID
     *
     * @return yyMMddHHmmssSSS（15位）
     */
    public static String getTenantId() {
        return DateUtils.getNowTimeStr(DateUtils.FORMAT_12);
    }

}

    