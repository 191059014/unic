package com.hb.unic.util.util;

import java.math.BigDecimal;

/**
 * ========== BigDecimal工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.BigDecimalUtils.java, v1.0
 * @date 2019年06月18日 17时16分
 */
public class BigDecimalUtils {

    /**
     * 默认精度
     */
    public static final Integer DEFAULT_SCALE = new Integer(2);

    /**
     * ########## 加法 ##########
     *
     * @param a1    值
     * @param a2    值
     * @param scale 精度
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal a1, BigDecimal a2, Integer... scale) {
        a1 = a1 == null ? BigDecimal.ZERO : a1;
        a2 = a2 == null ? BigDecimal.ZERO : a2;
        int resultScale = DEFAULT_SCALE;
        if (scale != null && scale.length > 0) {
            resultScale = scale[0];
        }
        return a1.add(a2).setScale(resultScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * ########## 减法 ##########
     *
     * @param s1    值
     * @param s2    值
     * @param scale 精度
     * @return BigDecimal
     */
    public static BigDecimal subtract(BigDecimal s1, BigDecimal s2, Integer... scale) {
        s1 = s1 == null ? BigDecimal.ZERO : s1;
        s2 = s2 == null ? BigDecimal.ZERO : s2;
        int resultScale = DEFAULT_SCALE;
        if (scale != null && scale.length > 0) {
            resultScale = scale[0];
        }
        return s1.subtract(s2).setScale(resultScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * ########## 乘法 ##########
     *
     * @param m1    值
     * @param m2    值
     * @param scale 精度
     * @return BigDecimal
     */
    public static BigDecimal multiply(BigDecimal m1, BigDecimal m2, Integer... scale) {
        m1 = m1 == null ? BigDecimal.ZERO : m1;
        m2 = m2 == null ? BigDecimal.ZERO : m2;
        int resultScale = DEFAULT_SCALE;
        if (scale != null && scale.length > 0) {
            resultScale = scale[0];
        }
        return m1.multiply(m2).setScale(resultScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * ########## 除法 ##########
     *
     * @param d1    分子
     * @param d2    分母
     * @param scale 精度
     * @return BigDecimal
     */
    public static BigDecimal divide(BigDecimal d1, BigDecimal d2, Integer... scale) {
        if (d1 == null || d2 == null || BigDecimal.ZERO.compareTo(d2) == 0) {
            return BigDecimal.ZERO;
        }
        int resultScale = DEFAULT_SCALE;
        if (scale != null && scale.length > 0) {
            resultScale = scale[0];
        }
        return d1.divide(d2, resultScale, BigDecimal.ROUND_HALF_UP);
    }

}
