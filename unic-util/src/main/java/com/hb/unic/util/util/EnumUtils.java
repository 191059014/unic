package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author Mr.huang
 * @since 2020/5/6 11:31
 */
public class EnumUtils {

    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(EnumUtils.class);

    /**
     * get方法的前缀
     */
    private static final String GET_METHOD_PREFIX = "get";

    /**
     * 枚举字段枚举
     */
    public enum KeysEnum {
        code, msg, name, value, desc
    }

    /**
     * 获取枚举所有元素
     *
     * @param enumClass 枚举类
     * @param <T>       枚举类
     * @return 枚举类所有对象元素
     */
    public static <T extends Enum> List<T> values(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        return Arrays.asList(enumConstants);
    }

    /**
     * 获取枚举中某个字段的值
     *
     * @param t            枚举对象
     * @param propertyName 字段名
     * @param <T>          枚举对像
     * @return 字段值
     */
    public static <T extends Enum> Object get(T t, String propertyName) {
        Method method = null;
        try {
            method = t.getClass().getMethod(GET_METHOD_PREFIX + StringUtils.upperFirst(propertyName));
            return method.invoke(t);
        } catch (NoSuchMethodException e) {
            LOGGER.info("get property from enum NoSuchMethodException: {}", LogExceptionWapper.getStackTrace(e));
        } catch (IllegalAccessException e) {
            LOGGER.info("get property from enum IllegalAccessException: {}", LogExceptionWapper.getStackTrace(e));
        } catch (InvocationTargetException e) {
            LOGGER.info("get property from enum InvocationTargetException: {}", LogExceptionWapper.getStackTrace(e));
        }
        return null;
    }

    /**
     * 匹配枚举某个状态值对应的对象
     *
     * @param enumClass           枚举类
     * @param comparePropertyName 属性名
     * @param compareValue        属性值
     * @param <T>                 枚举对象
     * @return 枚举对象
     */
    public static <T extends Enum> T stateOf(Class<T> enumClass, String comparePropertyName, Object compareValue) {
        List<T> tList = values(enumClass);
        for (T t : tList) {
            Object enumValue = get(t, comparePropertyName);
            if (Objects.equals(compareValue, enumValue)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 匹配枚举某个状态值对应的对象的某个属性值
     *
     * @param enumClass           枚举类
     * @param comparePropertyName 对比的属性名
     * @param compareValue        对比的属性值
     * @param targetPropertyName  结果字段名
     * @param <T>                 枚举对象
     * @return 枚举对象
     */
    public static <T extends Enum> Object stateOf(Class<T> enumClass, String comparePropertyName, Object compareValue, String targetPropertyName) {
        T t = stateOf(enumClass, comparePropertyName, compareValue);
        if (t == null) {
            return null;
        }
        return get(t, targetPropertyName);
    }

    /**
     * 下拉框功能
     *
     * @param enumClass         枚举类
     * @param namePropertyName  表示name的属性名称
     * @param valuePropertyName 表示value的属性名称
     * @param <T>               枚举泛型
     * @return List<Map>
     */
    public static <T extends Enum> List<Map<String, Object>> combobox(Class<T> enumClass, String namePropertyName, String valuePropertyName) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<? extends Enum> values = values(enumClass);
        values.forEach(e -> {
            Map<String, Object> map = new HashMap<>();
            map.put(KeysEnum.name.toString(), get(e, namePropertyName));
            map.put(KeysEnum.value.toString(), get(e, valuePropertyName));
            mapList.add(map);
        });
        return mapList;
    }

}
