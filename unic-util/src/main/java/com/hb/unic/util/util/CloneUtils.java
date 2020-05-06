package com.hb.unic.util.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

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
    protected static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * 拷贝对象
     *
     * @param source    被拷贝的对象
     * @param classType 拷贝的对象类型
     * @return 拷贝对象
     */
    public static <T, E> E clone(T source, Class<E> classType) {

        if (source == null) {
            return null;
        }
        E targetInstance = null;
        try {
            targetInstance = classType.newInstance();
        } catch (InstantiationException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("clone InstantiationException: {}", e);
            }
            return null;
        } catch (IllegalAccessException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("clone IllegalAccessException: {}", e);
            }
            return null;
        }
        BeanUtils.copyProperties(source, targetInstance);
        return targetInstance;
    }

}
