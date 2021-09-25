package com.hb.unic.config;

import com.hb.unic.config.service.IExceptionBoardService;
import com.hb.unic.config.service.IGlobalConfigService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 对相关工具进行包装，方便输出
 *
 * @version v0.1, 2020/7/24 15:24, create by huangbiao.
 */
@Component
public class ConfigContext implements InitializingBean {

    /**
     * 全局配置
     */
    @Autowired
    private IGlobalConfigService globalConfigService;
    private static IGlobalConfigService globalConfigServiceAgent;

    /**
     * 异常看板
     */
    @Autowired
    private IExceptionBoardService exceptionBoardService;
    private static IExceptionBoardService exceptionBoardServiceAgent;

    @Override
    public void afterPropertiesSet() {
        globalConfigServiceAgent = globalConfigService;
        exceptionBoardServiceAgent = exceptionBoardService;
    }

    /**
     * 全局配置
     *
     * @return 结果
     */
    public static IGlobalConfigService globalCfg() {
        return globalConfigServiceAgent;
    }

    /**
     * 异常看板
     *
     * @return 结果
     */
    public static IExceptionBoardService errBoard() {
        return exceptionBoardServiceAgent;
    }

}
