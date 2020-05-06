package com.hb.unic.logger.config;

import com.hb.unic.base.util.FilterUtils;
import com.hb.unic.logger.filter.TraceIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * traceId过滤器设置
 *
 * @author Mr.huang
 * @since 2020/5/6 11:09
 */
@Configuration
public class TraceIdFilterConfig {

    /**
     * traceIdFilter
     */
    @Bean
    public FilterRegistrationBean traceIdFilter() {
        return FilterUtils.buildSimpleFilter(new TraceIdFilter(), "/controller/*", "traceIdFilter", true, 0);
    }

}
