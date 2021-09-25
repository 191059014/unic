package com.hb.unic.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * ========== 字典工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.DictionaryUtils.java, v1.0
 * @date 2019年06月05日 15时02分
 */
public class DictionaryUtils {

    /**
     * 中文的金额
     *
     * @param amount 金额
     * @return 中文
     */
    public static String convertToChineseAmount(BigDecimal amount) {
        amount = amount.setScale(2, 4);
        BigDecimal zero = new BigDecimal("0.00");
        zero = zero.setScale(2, 4);
        if (zero.compareTo(amount) == 0) return "零元整";
        if (amount.compareTo(new BigDecimal("999999999999")) > 0) return "";
        String str = "";
        String[] number = {"零 ", "壹 ", "贰 ", "叁 ", "肆 ", "伍 ", "陆 ", "柒 ", "捌 ", "玖 "};
        String[] units = {"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};
        double temp = amount.doubleValue();
        int i = 0;
        while (temp >= 10.0D) {
            int n = (int) (temp % 10.0D);
            temp /= 10.0D;
            str = str.trim() + units[(i++)].trim() + number[n].trim();
        }
        str = str.trim() + units[(i++)].trim() + number[((int) temp)].trim();
        StringBuffer sb = new StringBuffer(str.trim());
        sb.reverse();
        str = sb.toString();
        int dolt1 = (int) (amount.multiply(new BigDecimal("100")).longValue() % 10L);
        int dolt2 = (int) (amount.multiply(new BigDecimal("10")).longValue() % 10L);
        if ((dolt1 == 0) && (dolt2 == 0))
            str = str + "整";
        else if (dolt1 != 0) {
            if (dolt2 != 0) str = str + number[dolt2].trim() + "角" + number[dolt1].trim() + "分";
            else
                str = str + "零" + number[dolt1].trim() + "分";
        } else if (dolt2 != 0) {
            str = str + number[dolt2].trim() + "角整";
        }
        while ((str.indexOf("零亿") != -1) || (str.indexOf("零万") != -1) || (str.indexOf("零仟") != -1) ||
                (str
                        .indexOf("零佰") !=
                        -1) || (str.indexOf("零拾") != -1) ||
                (str
                        .indexOf("零元") !=
                        -1)) {
            str = str.replaceAll("零亿", "亿");
            str = str.replaceAll("零万", "万");
            str = str.replaceAll("零仟", "零");
            str = str.replaceAll("零佰", "零");
            str = str.replaceAll("零拾", "零");
            if (str.startsWith("零元")) str = str.replaceAll("零元", "");
            else {
                str = str.replaceAll("零元", "元");
            }
        }
        if (str.indexOf("亿万") != -1) {
            str = str.replaceAll("亿万", "亿零");
        }
        if (str.indexOf("万仟") != -1) {
            str = str.replaceAll("万仟", "万零");
        }
        String req = ".*佰亿.仟.*";
        if (str.matches(req)) {
            str = str.substring(0, str.indexOf("佰亿") + 2) + "零" + str.substring(str.indexOf("佰亿") + 2);
        }
        req = ".*拾亿.仟.*";
        if (str.matches(req)) {
            str = str.substring(0, str.indexOf("拾亿") + 2) + "零" + str.substring(str.indexOf("拾亿") + 2);
        }
        req = ".*佰万.仟.*";
        if (str.matches(req)) {
            str = str.substring(0, str.indexOf("佰万") + 2) + "零" + str.substring(str.indexOf("佰万") + 2);
        }
        req = ".*拾万.仟.*";
        if (str.matches(req)) {
            str = str.substring(0, str.indexOf("拾万") + 2) + "零" + str.substring(str.indexOf("拾万") + 2);
        }
        while (str.indexOf("零元") != -1) {
            str = str.replaceAll("零元", "元");
        }
        if (((str.indexOf("拾元") != -1) || (str.indexOf("佰元") != -1) || (str.indexOf("仟元") != -1) ||
                (str
                        .indexOf("万元") !=
                        -1) || (str.indexOf("亿元") != -1)) && (str.indexOf("角") != -1)) {
            str = str.substring(0, str.indexOf("元") + 1) + "零" + str.substring(str.indexOf("元") + 1);
        }
        while (str.indexOf("零零") != -1) {
            str = str.replaceAll("零零", "零");
        }
        if (((str.startsWith("整")) || (str.startsWith("零"))) && (str.length() > 1)) {
            str = str.substring(1);
        }
        return str;
    }

    /**
     * 中文数字
     *
     * @param str 字符
     * @return 中文
     */
    public static String getMaxNum(String str) {
        Map map = new HashMap();
        map.put("0", "零");
        map.put("1", "壹");
        map.put("2", "贰");
        map.put("3", "叁");
        map.put("4", "肆");
        map.put("5", "伍");
        map.put("6", "陆");
        map.put("7", "柒");
        map.put("8", "捌");
        map.put("9", "玖");
        map.put(".", ".");
        return (String) map.get(str);
    }

    /**
     * 中文的金额
     *
     * @param money 金额
     * @return 中文
     */
    public static String convertToChineseAmount(double money) {
        char[] s1 = {38646, '壹', 36144, '叁', 32902, '伍', 38470, '柒', '捌', '玖'};
        char[] s4 = {'分', 35282, '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万'};
        String str = String.valueOf(Math.round(money * 100.0D + 0.001D));
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(str.length() - 1 - i) - '0';
            result = s1[n] + "" + s4[i] + result;
        }
        result = result.replaceAll("零仟", "零");
        result = result.replaceAll("零佰", "零");
        result = result.replaceAll("零拾", "零");
        result = result.replaceAll("零亿", "亿");
        result = result.replaceAll("零万", "万");
        result = result.replaceAll("零元", "元");
        result = result.replaceAll("零角", "零");
        result = result.replaceAll("零分", "零");
        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零亿", "亿");
        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零万", "万");
        result = result.replaceAll("零零", "零");
        result = result.replaceAll("零元", "元");
        result = result.replaceAll("亿万", "亿");
        result = result.replaceAll("零$", "");
        result = result.replaceAll("元$", "元整");
        return result;
    }
}
