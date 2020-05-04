package com.hb.unic.base.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ========== Spring bean容器 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.container.BaseServiceLocator.java, v1.0
 * @date 2019年06月02日 01时23分
 */
@Component
public class BaseServiceLocator implements ApplicationContextAware {

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceLocator.class);

    //spring上下文
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BaseServiceLocator.applicationContext == null) {
            LOGGER.info("set applicationContext complete");
            BaseServiceLocator.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
