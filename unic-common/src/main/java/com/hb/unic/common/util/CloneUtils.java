package com.hb.unic.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== 复制工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.CloneUtils.java, v1.0
 * @date 2019年06月24日 23时44分
 */
public class CloneUtils {

    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(CloneUtils.class);

    /**
     * 复制对象属性
     *
     * @param source
     *            源
     * @param tClass
     *            目标
     * @return 目标对象
     */
    public static <S, T> T copyProperties(S source, Class<T> tClass) {
        if (source == null) {
            return null;
        }
        try {
            T t = tClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量复制对象属性
     *
     * @param sourceList
     *            源
     * @param tClass
     *            目标对象class
     */
    public static <S, T> List<T> copyPropertiesList(List<S> sourceList, Class<T> tClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (S items : sourceList) {
            list.add(copyProperties(items, tClass));
        }
        return list;
    }

    /**
     * 将对象转换为map
     *
     * @param bean
     *            bean
     * @return Map
     */
    public static <T> Map<String, Object> bean2Map(T bean) {
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            // 获取JavaBean的描述器
            BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);
            // 获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();
            // 对属性迭代
            for (PropertyDescriptor pd : pds) {
                // 属性名称
                String propertyName = pd.getName();
                // 属性值, 用getter方法获取
                Method m = pd.getReadMethod();
                Object properValue = m.invoke(bean);// 用对象执行getter方法获得属性值
                if (properValue != null && !"".equals(properValue.toString())) {
                    // 把属性名-属性值 存到Map中
                    map.put(propertyName, properValue);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    /**
     * 将map装换为对象
     *
     * @param map
     *            map
     * @param beanClass
     *            beanClass
     * @return T
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> beanClass) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            T t = beanClass.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param beanList
     *            list集合
     * @return List
     */
    public static <T> List<Map<String, Object>> beans2Maps(List<T> beanList) {
        if (beanList == null || beanList.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        beanList.forEach(bean -> list.add(bean2Map(bean)));
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param mapList
     *            map集合list
     * @param clazz
     *            bean
     * @return List<T>
     */
    public static <T> List<T> maps2Beans(List<Map<String, Object>> mapList, Class<T> clazz) {
        if (mapList == null || mapList.isEmpty()) {
            return null;
        }
        List<T> list = new ArrayList<>();
        mapList.forEach(map -> list.add(map2Bean(map, clazz)));
        return list;
    }

}
