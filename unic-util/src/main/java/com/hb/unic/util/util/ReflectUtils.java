package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

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
            LOGGER.error("Method getType error: {}", LogExceptionWapper.getStackTrace(e));
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

    /**
     * 获取所有属性名和属性值
     *
     * @param t   对象
     * @param <T> 对象类型
     * @return map
     */
    public static <T> Map<String, Object> getAllFieldsExcludeStaticAndFinal(T t) {
        Map<String, Object> map = new HashMap<>();
        Field[] allFields = getAllFields(t.getClass());
        try {
            for (Field field : allFields) {
                int fieldModifiers = field.getModifiers();
                if (Modifier.isStatic(fieldModifiers) || Modifier.isFinal(fieldModifiers)) {
                    continue;
                }
                String name = field.getName();
                field.setAccessible(true);
                Object value = field.get(t);
                map.put(name, value);
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("getAllFieldsExcludeStaticAndFinal error: {}", LogExceptionWapper.getStackTrace(e));
        }
        return map;
    }

}
