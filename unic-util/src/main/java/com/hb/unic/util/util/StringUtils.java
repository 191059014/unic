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

    /**
     * 首字母大写
     *
     * @param value 字符串
     * @return 转换后的值
     */
    public static String upperFirst(String value) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            value = value.trim();
            String first = value.substring(0, 1);
            String last = value.substring(1);
            return first.toUpperCase() + last;
        }
        return value;
    }

    /**
     * 首字母小写
     *
     * @param value 字符串
     * @return 转换后的值
     */
    public static String lowerFirst(String value) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            value = value.trim();
            String first = value.substring(0, 1);
            String last = value.substring(1);
            return first.toLowerCase() + last;
        }
        return value;
    }

    /**
     * 截取src中首次包含dest字符串前面的内容
     *
     * @param src  字符串
     * @param dest 目标
     * @return 字符串
     */
    public static String before(String src, String dest) {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String first = src.substring(0, pos);

        return first;
    }

    /**
     * 截取src中首次包含dest字符串后面的内容
     *
     * @param src  字符串
     * @param dest 目标
     * @return 字符串
     */
    public static String after(String src, String dest) {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String last = src.substring(pos + 1);

        return last;
    }

    /**
     * 截取src中最后一次包含dest字符串前面的内容
     *
     * @param src  字符串
     * @param dest 目标
     * @return 字符串
     */
    public static String lastBefore(String src, String dest) {
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }
        String first = src.substring(0, pos);

        return first;
    }

    /**
     * 截取src中最后一次包含dest字符串后面的内容
     *
     * @param src  字符串
     * @param dest 目标
     * @return 字符串
     */
    public static String lastAfter(String src, String dest) {
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }
        String last = src.substring(pos + 1);

        return last;
    }

}
