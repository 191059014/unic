package com.hb.unic.base;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 对相关工具进行包装，方便输出
 *
 * @version v0.1, 2020/7/24 15:24, create by huangbiao.
 */
@Component
public class BaseContext implements InitializingBean {

    /**
     * Environment
     */
    @Autowired
    private Environment environment;
    private static Environment environmentAgent;

    @Override
    public void afterPropertiesSet() {
        environmentAgent = environment;
    }

    /**
     * 获取全局配置服务
     *
     * @return 结果
     */
    public static Environment env() {
        return environmentAgent;
    }

}
