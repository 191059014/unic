package com.hb.unic.base;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * ========== base模块上下文 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.ServiceBaseContext.java, v1.0
 * @date 2019年08月17日 00时10分
 */
@Primary
@Component
@Configuration
public class BaseContext implements InitializingBean {

    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Properties props = null;

    /**
     * ########## 获取环境属性值 ##########
     *
     * @param key 键
     * @return 值
     */
    public static String getValue(String key) {
        return props.getProperty(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加载配置
        loadProperties();
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
            applicationProperties.setResources(resources);
            props = applicationProperties.getObject();
        } catch (IOException e) {
            logger.error("load yml config error!");
        }
    }

}
