package com.hb.unic.logger.config;

import com.hb.unic.logger.filter.TraceIdMdcHttpFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 拦截器
 *
 * @author huangbiao
 * @version v 0.1, 2020年12月6日 下午2:18:44, modify by huangbiao
 */
@Slf4j
@Configuration
public class EnableTraceIdFilterAutoConfiguration {

    /**
     * traceId的mdc方式
     */
    @Bean
    public FilterRegistrationBean traceIdMdcHttpFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceIdMdcHttpFilter());
        registration.addUrlPatterns("/*");
        registration.setName("traceIdMdcHttpFilter");
        registration.setEnabled(true);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("{} register complete, urlPatterns: [{}], order: {}", TraceIdMdcHttpFilter.class.getSimpleName(),
            registration.getUrlPatterns(), registration.getOrder());
        return registration;
    }

}
