package com.hb.unic.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * ========== 反射工具类 ==========
 *
 * @author Mr.huang
 * @version v1.0
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
     * @param entityName
     *            实体类名称
     * @return 类型
     */
    public static Class<?> getType(String entityName) {
        try {
            return Class.forName(entityName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有属性
     *
     * @param c
     *            类
     * @return 属性数组
     */
    public static Field[] getAllFields(Class<?> c) {
        Field[] fs = c.getDeclaredFields();
        if (c.getSuperclass() != Object.class) {
            fs = ArrayUtils.addAll(fs, getAllFields(c.getSuperclass()));
        }
        return fs;
    }

    /**
     * 获取所有属性名和属性值
     *
     * @param t
     *            对象
     * @param <T>
     *            对象类型
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
            return map;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     *            属性名
     * @param t
     *            实例对象
     */
    public static <T> void setPropertyValue(String fieldName, Object value, T t) {
        try {
            // 获取obj类的字节文件对象
            Class tClass = t.getClass();
            // 获取该类的成员变量
            Optional<Field> optionalField = getFieldByName(tClass, fieldName);
            if (optionalField.isPresent()) {
                Field field = optionalField.get();
                // 取消语言访问检查
                field.setAccessible(true);
                // 给变量赋值
                field.set(t, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过属性名获取类属性
     *
     * @param c
     *            类
     * @param fieldName
     *            属性名
     * @return Optional<Field>
     */
    public static Optional<Field> getFieldByName(Class<?> c, String fieldName) {
        Field[] allFields = getAllFields(c);
        return Stream.of(allFields).filter(field -> fieldName.equals(field.getName())).findFirst();
    }

}
