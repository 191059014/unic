package com.hb.unic.util.util;

import com.hb.unic.logger.util.LogExceptionWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * ========== 字符串工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.StringUtils.java, v1.0
 * @date 2019年07月15日 13时34分
 */
public class StringUtils {

    /**
     * the constant LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

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

        return src.substring(0, pos);
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

        return src.substring(pos + 1);
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

        return src.substring(0, pos);
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

        return src.substring(pos + 1);
    }

    /**
     * 将下划线风格替换为驼峰风格
     *
     * @param underlineStr 要转换的列名
     * @return 驼峰字段
     */
    public static String underline2Hump(String underlineStr) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < underlineStr.length(); i++) {
            char c = underlineStr.charAt(i);
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 将驼峰风格替换为下划线风格
     *
     * @param humpStr 要转换的列名
     * @return 下划线风格字段
     */
    public static String hump2Underline(String humpStr) {
        StringBuilder sb = new StringBuilder(humpStr);
        int temp = 0;
        for (int i = 0; i < humpStr.length(); i++) {
            if (Character.isUpperCase(humpStr.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 将byte数组转换为字符串
     *
     * @param filedByte 数组
     * @param charset   编码
     * @return 字符串
     */
    public static String toString(byte[] filedByte, String charset) {
        String filed = null;
        try {
            filed = new String(filedByte, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("byte arr to string error:{}", LogExceptionWapper.getStackTrace(e));
        }
        return filed;
    }

    /**
     * 将数值转换为金额的中文显示方式
     *
     * @param n 金额数值
     * @return 金额的中文显示方式
     */
    public static String digitUppercase(double n) {
        String[] fraction = {"角", "分"};
        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[][] unit = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
        String head = n < 0.0D ? "负" : "";
        n = Math.abs(n);
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s = s + new StringBuilder().append(digit[((int) (Math.floor(n * 10.0D * Math.pow(10.0D, i)) % 10.0D))]).append(fraction[i]).toString().replaceAll("(零.)+", "");
        }
        if (s.length() < 1) {
            s = "整";
        }
        int integerPart = (int) Math.floor(n);
        for (int i = 0; (i < unit[0].length) && (integerPart > 0); i++) {
            String p = "";
            for (int j = 0; (j < unit[1].length) && (n > 0.0D); j++) {
                p = digit[(integerPart % 10)] + unit[1][j] + p;
                integerPart /= 10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }

    /**
     * StringBuilder连接字符串
     *
     * @return 字符串
     */
    public static String joint(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    /**
     * 字符串是否包含文本
     *
     * @param str 原字符串
     * @return true
     */
    public static boolean hasText(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static void main(String[] args) {
        System.out.println(digitUppercase(141241414.5));
    }

}
