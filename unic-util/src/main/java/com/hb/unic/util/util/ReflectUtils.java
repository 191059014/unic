package com.hb.unic.util.util;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * ========== 反射工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.Logger.java, v1.0
 * @date 2019年07月16日 11时42分
 */
public class ReflectUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * 通过类名获取类型
     *
     * @param entityName 实体类名称
     * @return 类型
     */
    public static Class<?> getType(String entityName) {
        try {
            return Class.forName(entityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("Method getType:", e);
        }
        return null;
    }

    /**
     * 获取所有属性
     *
     * @param c 类
     * @return 属性数组
     */
    public static Field[] getAllFields(Class<?> c) {
        Field[] fs = c.getDeclaredFields();
        if (c.getSuperclass() != Object.class) {
            fs = (Field[]) ArrayUtils.addAll(fs, getAllFields(c.getSuperclass()));
        }
        return fs;
    }

}
