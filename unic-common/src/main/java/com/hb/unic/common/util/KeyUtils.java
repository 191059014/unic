package com.hb.unic.common.util;

/**
 * 生成唯一key工具类
 *
 * @version v0.1, 2020/9/1 16:17, create by huangbiao.
 */
public class KeyUtils {

    /**
     * 生成唯一key
     *
     * @param pkFlag
     *            key标识
     * @return 标识+yyMMddHHmmssSSS（15位）+随机数（6位）
     */
    public static String getUniqueKey(String pkFlag) {
        return getNowTimeKey(pkFlag, DateUtils.FORMAT_6, 6);
    }

    /**
     * pkFlag+时间戳+随机数
     */
    public static String getNowTimeKey(String pkFlag, String format, int randomLength) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pkFlag != null) {
            stringBuilder.append(pkFlag);
        }
        stringBuilder.append(DateUtils.getNowTimeStr(format));
        if (randomLength > 0) {
            stringBuilder.append(RandomUtils.getRandomOfLength(randomLength));
        }
        return stringBuilder.toString();
    }

}
