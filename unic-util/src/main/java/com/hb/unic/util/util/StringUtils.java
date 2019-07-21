package com.hb.unic.util.util;

/**
 * ========== 字符串工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.StringUtils.java, v1.0
 * @date 2019年07月15日 13时34分
 */
public class StringUtils {

    /**
     * ########## 左边补0 ##########
     *
     * @param str    原字符串
     * @param length 期望的长度
     * @return 补0后的字符串
     */
    public static String fillZero(String str, int length) {
        int addLength = length - str.length();
        if (addLength == 0) {
            return str;
        }
        StringBuilder zero = new StringBuilder();
        for (int i = 0; i < addLength; i++) {
            zero.append("0");
        }
        zero.append(str);
        return zero.toString();
    }

    /**
     * ########## 转换null为空字符串 ##########
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return String.valueOf(obj);
    }

}
