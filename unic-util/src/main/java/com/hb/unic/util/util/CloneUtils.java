package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;
import org.apache.commons.beanutils.BeanUtils;

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
     * 复制属性, 可以是不通类型javabean
     *
     * @param source 源
     * @param tClass 目标
     * @return 目标对象
     */
    public static <S, T> T copyProperties(S source, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("copyProperties Exception: {}", LogExceptionWapper.getStackTrace(e));
            }
            return null;
        }

    }

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
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("cloneBean Exception: {}", LogExceptionWapper.getStackTrace(e));
            }
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
    public static <T> Map<String, Object> bean2Map(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            try {
                //获取JavaBean的描述器
                BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);
                //获取属性描述器
                PropertyDescriptor[] pds = b.getPropertyDescriptors();
                //对属性迭代
                for (PropertyDescriptor pd : pds) {
                    //属性名称
                    String propertyName = pd.getName();
                    //属性值, 用getter方法获取
                    Method m = pd.getReadMethod();
                    Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值
                    if (properValue != null && !"".equals(properValue.toString())) {
                        //把属性名-属性值 存到Map中
                        map.put(propertyName, properValue);
                    }
                }
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("bean2Map Exception: {}", LogExceptionWapper.getStackTrace(e));
                }
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象, 要求map的value的类型和bean属性的类型一致, 否则会出现异常
     *
     * @param map       map
     * @param beanClass beanClass
     * @return T
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> beanClass) {
        try {
            T t = beanClass.newInstance();
            BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("map2Bean Exception: {}", LogExceptionWapper.getStackTrace(e));
            }
            return null;
        }
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param beanList list集合
     * @return List
     */
    public static <T> List<Map<String, Object>> beans2Maps(List<T> beanList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (beanList != null && beanList.size() > 0) {
            beanList.forEach(bean -> list.add(bean2Map(bean)));
        }
        return list;
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
