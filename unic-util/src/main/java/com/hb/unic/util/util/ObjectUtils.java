package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Object工具类
 *
 * @version v0.1, 2020/7/31 16:04, create by huangbiao.
 */
public class ObjectUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 将多个Object元素转换为Object数组
     *
     * @param objs 多个Object元素
     * @return Object数组
     */
    public static Object[] asArray(Object... objs) {
        return objs;
    }

    /**
     * 将Object转换为Integer
     *
     * @param value 待转换的值
     * @return Integer
     */
    public static Integer asInteger(Object value) {
        if ((value == null) || (value.toString().equals("")) || (value.toString().length() == 0))
            return null;
        return Integer.valueOf(value.toString());
    }

    /**
     * 将Object转换为Long
     *
     * @param value 待转换的值
     * @return Long
     */
    public static Long asLong(Object value) {
        if ((value == null) || (value.toString().equals("")) || (value.toString().length() == 0))
            return null;
        return Long.valueOf(value.toString());
    }

    /**
     * 将Object转换为Long
     *
     * @param value 待转换的值
     * @return Long
     */
    public static BigDecimal asBigDecimal(Object value) {
        if ((value == null) || (value.toString().equals("")) || (value.toString().length() == 0))
            return BigDecimal.ZERO;
        return new BigDecimal(value.toString());
    }

    /**
     * 通过完整类名实例化对象
     *
     * @param className 完整类名
     * @return 对象
     */
    public static Object newInstance(String className) {
        if (StringUtils.isNotBlank(className)) {
            Class clz = null;
            try {
                clz = Class.forName(className);
                if (clz != null) {
                    return clz.newInstance();
                }
            } catch (Exception e) {
                LOGGER.error("newInstance error: {}", LogExceptionWapper.getStackTrace(e));
            }
        }
        return null;
    }

}

    