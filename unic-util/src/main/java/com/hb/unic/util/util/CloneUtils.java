package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;
import org.apache.commons.beanutils.BeanUtils;

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
     * 复制java bean
     *
     * @param bean 被复制的bean
     * @param <T>  bean类型
     * @return 复制后的bean
     */
    public static <T> T cloneBean(T bean) {
        try {
            return bean == null ? null : (T) BeanUtils.cloneBean(bean);
        } catch (Exception e) {
            LOGGER.error("cloneBean Exception: {}", LogExceptionWapper.getStackTrace(e));
            return null;
        }
    }

    /**
     * 复制List
     *
     * @param tList 被复制的list
     * @param <T>   bean类型
     * @return 复制后的bean
     */
    public static <T> List<T> cloneList(List<T> tList) {
        return new ArrayList<>(tList);
    }

    /**
     * 复制Map
     *
     * @param map 被复制的map
     * @return 复制后的Map
     */
    public static Map<String, Object> cloneMap(Map<String, Object> map) {
        return new HashMap<>(map);
    }

    /**
     * 将对象转换为map
     *
     * @param bean bean
     * @return Map
     */
    public static <T> Map<String, String> bean2Map(T bean) {
        if (bean == null) {
            return null;
        }
        try {
            Map<String, String> map = BeanUtils.describe(bean);
            if (map != null) {
                map.remove("class");
            }
            return map;
        } catch (Exception e) {
            LOGGER.info("beanToMap exception: {}", e);
            return null;
        }
    }

    /**
     * 将List<T>转换为List<Map<String, String>>
     *
     * @param beanList list集合
     * @return List
     */
    public static <T> List<Map<String, String>> beans2Maps(List<T> beanList) {
        List<Map<String, String>> list = new ArrayList<>();
        if (beanList != null && beanList.size() > 0) {
            Map<String, Object> map = null;
            beanList.forEach(bean -> list.add(bean2Map(bean)));
        }
        return list;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map       map
     * @param beanClass beanClass
     * @return T
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }
        try {
            T obj = beanClass.newInstance();
            BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {
            LOGGER.error("mapToBean Exception: {}", e);
            return null;
        }
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param mapList map集合list
     * @param clazz   bean
     * @return List<T>
     */
    public static <T> List<T> maps2Beans(List<Map<String, Object>> mapList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (mapList != null && mapList.size() > 0) {
            mapList.forEach(map -> list.add(map2Bean(map, clazz)));
        }
        return list;
    }

}
