package com.hb.unic.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Properties;

/**
 * ========== 全局的属性配置 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.GlobalProperties.java, v1.0
 * @date 2019年08月17日 00时10分
 */
@Primary
@Component
public class GlobalProperties implements InitializingBean {

    /**
     * The LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalProperties.class);

    /**
     * yml配置
     */
    @Autowired
    @Qualifier("yamlProperties")
    private YamlPropertiesFactoryBean yamlProperties;

    /**
     * 所有属性
     */
    private static Properties props = null;

    /**
     * 获取String
     *
     * @param key 键
     * @return 值
     */
    public static String getString(String key) {
        return props.getProperty(key);
    }

    /**
     * 获取Long
     *
     * @param key 键
     * @return 值
     */
    public static Long getLong(String key) {
        String value = props.getProperty(key);
        return value == null ? null : Long.parseLong(value);
    }

    /**
     * 获取Integer
     *
     * @param key 键
     * @return 值
     */
    public static Integer getInteger(String key) {
        String value = props.getProperty(key);
        return value == null ? null : Integer.parseInt(value);
    }

    /**
     * 获取Double
     *
     * @param key 键
     * @return 值
     */
    public static Double getDouble(String key) {
        String value = props.getProperty(key);
        return value == null ? null : Double.parseDouble(value);
    }

    /**
     * 获取BigDecimal
     *
     * @param key 键
     * @return 值
     */
    public static BigDecimal getBigDecimal(String key) {
        String value = props.getProperty(key);
        return value == null ? BigDecimal.ZERO : new BigDecimal(value);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加载配置
        props = yamlProperties.getObject();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("################################################################# GlobalProperties #################################################################");
            Enumeration<?> enumeration = props.propertyNames();
            while (enumeration.hasMoreElements()) {
                Object propertyName = enumeration.nextElement();
                LOGGER.debug(propertyName + " => " + props.get(propertyName));
            }
            LOGGER.debug("################################################################# GlobalProperties #################################################################");

        }
    }

    /**
     * ########## 加载配置 ##########
     */
    private void loadProperties() {
        YamlPropertiesFactoryBean applicationProperties = new YamlPropertiesFactoryBean();
        Resource[] resources = null;
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            resources = resolver.getResources("classpath*:config/service-*-config.yml");
            if (resources != null) {
                for (Resource resource : resources) {
                    LOGGER.info("load {} complete", resource.getFilename());
                }
            }
            applicationProperties.setResources(resources);
            props = applicationProperties.getObject();
        } catch (IOException e) {
            LOGGER.error("load yml config error: {}", e);
        }
    }

}
