package com.hb.unic.common.util;

import org.springframework.util.StringUtils;

/**
 * ========== 字符串工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.StrUtils.java, v1.0
 * @date 2019年07月15日 13时34分
 */
public class StrUtils {

    /**
     * 空字符串
     */
    private static final String EMPTY = "";

    /**
     * 是否是空的字符串
     *
     * @param str
     *            字符串
     * @return true为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str) || "undefined".equals(str);
    }

    /**
     * 判断是否有一个是空的
     * 
     * @param arr
     *            数组
     * @return 结果
     */
    public static boolean isAnyEmpty(String... arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        for (String str : arr) {
            if (StringUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断全部为空
     * 
     * @param arr
     *            数组
     * @return 结果
     */
    public static boolean isAllEmpty(String... arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        for (String str : arr) {
            if (!StringUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否包含文本
     *
     * @param str
     *            原字符串
     * @return true
     */
    public static boolean hasText(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 去首尾空格
     *
     * @param str
     *            原字符串
     * @return true
     */
    public static String trim(String str) {
        if (StringUtils.isEmpty(str)) {
            return EMPTY;
        }
        return str.trim();
    }

    /**
     * 在开头或结尾填充0
     *
     * @param str
     *            原字符串
     * @param targetLength
     *            目标长度
     * @param left
     *            是否补0在起始位置
     * @return 字符串
     */
    public static String fillZero(String str, int targetLength, boolean left) {
        if (StringUtils.isEmpty(str)) {
            return EMPTY;
        }
        int length = str.length();
        if (length >= targetLength) {
            return str;
        }
        StringBuilder zeroSb = new StringBuilder();
        for (int i = 0; i < targetLength - length; i++) {
            zeroSb.append("0");
        }
        if (left) {
            zeroSb.append(str);
        } else {
            zeroSb.insert(0, str);
        }
        return zeroSb.toString();
    }

    /**
     * 转换Object为字符串
     *
     * @param obj
     *            对象
     * @return 字符串
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return EMPTY;
        }
        return String.valueOf(obj);
    }

    /**
     * 首字母大写
     *
     * @param str
     *            字符串
     * @return 转换后的值
     */
    public static String upperFirst(String str) {
        if (StringUtils.isEmpty(str)) {
            return EMPTY;
        }
        str = str.trim();
        String first = str.substring(0, 1);
        String last = str.substring(1);
        return first.toUpperCase() + last;
    }

    /**
     * 首字母小写
     *
     * @param str
     *            字符串
     * @return 转换后的值
     */
    public static String lowerFirst(String str) {
        if (StringUtils.isEmpty(str)) {
            return EMPTY;
        }
        str = str.trim();
        String first = str.substring(0, 1);
        String last = str.substring(1);
        return first.toLowerCase() + last;
    }

    /**
     * 获取文本中两个字符串中间的文本，只返回首个结果
     * 
     * @param str
     *            文本
     * @param startMark
     *            开始标记
     * @param endMark
     *            结束标记
     * @return 结果
     */
    public static String between(String str, String startMark, String endMark) {
        if (isAllEmpty(str, startMark, endMark)) {
            return EMPTY;
        }
        int startIndex = str.indexOf(startMark);
        if (startIndex < 0) {
            return EMPTY;
        }
        int endIndex = str.indexOf(endMark);
        if (endIndex < 0 || endIndex < startIndex) {
            return EMPTY;
        }
        return str.substring(startIndex + startMark.length(), endIndex);
    }

    /**
     * 截取src中首次包含dest字符串前面的内容
     *
     * @param src
     *            字符串
     * @param dest
     *            目标
     * @return 字符串
     */
    public static String before(String src, String dest) {
        if (StringUtils.isEmpty(src)) {
            return EMPTY;
        }
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }

        return src.substring(0, pos);
    }

    /**
     * 截取src中首次包含dest字符串后面的内容
     *
     * @param src
     *            字符串
     * @param dest
     *            目标
     * @return 字符串
     */
    public static String after(String src, String dest) {
        if (StringUtils.isEmpty(src)) {
            return EMPTY;
        }
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }

        return src.substring(pos + 1);
    }

    /**
     * 截取src中最后一次包含dest字符串前面的内容
     *
     * @param src
     *            字符串
     * @param dest
     *            目标
     * @return 字符串
     */
    public static String lastBefore(String src, String dest) {
        if (StringUtils.isEmpty(src)) {
            return EMPTY;
        }
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }

        return src.substring(0, pos);
    }

    /**
     * 截取src中最后一次包含dest字符串后面的内容
     *
     * @param src
     *            字符串
     * @param dest
     *            目标
     * @return 字符串
     */
    public static String lastAfter(String src, String dest) {
        if (StringUtils.isEmpty(src)) {
            return EMPTY;
        }
        int pos = src.lastIndexOf(dest);
        if (pos == -1) {
            return src;
        }

        return src.substring(pos + 1);
    }

    /**
     * StringBuilder连接字符串
     *
     * @return 字符串
     */
    public static String joint(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objArr.length; i++) {
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    /**
     * 获取asc码
     *
     * @param obj
     *            obj
     *
     * @return asc码
     */
    public static String getAscii(Object obj) {
        String str = obj == null ? EMPTY : obj.toString();
        if (!hasText(str)) {
            return EMPTY;
        }
        StringBuilder indexSb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char[] strChar = str.substring(i, i + 1).toCharArray();
            for (char s : strChar) {
                indexSb.append((byte)s);
            }
        }
        return indexSb.toString();
    }

}
