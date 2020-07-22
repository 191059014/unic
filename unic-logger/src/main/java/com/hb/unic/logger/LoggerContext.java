package com.hb.unic.logger;

import com.hb.unic.logger.common.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ========== 全局的属性配置 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.GlobalProperties.java, v1.0
 * @date 2019年08月17日 00时10分
 */
@Primary
@Component
public class LoggerContext implements InitializingBean {

    /**
     * The LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerContext.class);

    /**
     * 应用名称
     */
    @Value("${spring.application.name:default}")
    private String appName;

    /**
     * 配置集合
     */
    private static Map<String, String> maps = new HashMap<>(8);

    @Override
    public void afterPropertiesSet() throws Exception {
        maps.put(Consts.APP_NAME, appName);
        LOGGER.debug("LoggerContext load properties: {}", maps);
    }

    /**
     * 获取属性值
     *
     * @param key 属性名
     * @return 值
     */
    public static String getValue(String key) {
        return maps.get(key);
    }

}
