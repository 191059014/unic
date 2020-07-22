package com.hb.unic.logger.filter;

import com.hb.unic.logger.LoggerContext;
import com.hb.unic.logger.common.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 拦截器
 *
 * @author renby
 * @version $Id: FilterConfiguration.java, v 0.1 2019年12月6日 下午2:18:44 renby Exp $
 */
@Configuration
public class FilterConfiguration {

    /**
     * org.slf4j.Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterConfiguration.class);

    /**
     * traceId过滤器
     */
    @Bean
    public FilterRegistrationBean traceIdHttpFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 注入过滤器
        registration.setFilter(new TraceIdHttpFilter());
        // 过滤掉所有请求
        String urlPatterns = "/controller/*";
        registration.addUrlPatterns(urlPatterns);
        // 过滤器名称
        registration.setName("traceIdHttpFilter");
        // 是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        // 过滤器顺序
        registration.setOrder(-99);
        LOGGER.info("{} register complete, urlPatterns: [{}]", "traceIdHttpFilter", urlPatterns);
        return registration;
    }

}
