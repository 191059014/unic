package com.hb.unic.logger.filter;

import com.hb.unic.logger.LoggerContext;
import com.hb.unic.logger.common.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;

/**
 * 拦截器
 *
 * @author huangbiao
 * @version $Id: TraceIdFilterConfiguration.java, v 0.1 2020年12月6日 下午2:18:44 huangbiao Exp $
 */
@Configuration
public class TraceIdFilterConfiguration {

    /**
     * org.slf4j.Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdFilterConfiguration.class);

    /**
     * traceId的默认过滤器
     */
    @Bean
    @DependsOn("loggerContext")
    @ConditionalOnExpression("'common'.equals('${unicLogger.traceIdFilterMode}')")
    public FilterRegistrationBean traceIdDefaultHttpFilter() {
        String urlPatterns = LoggerContext.getValue(Consts.HTTP_FILTER_URL_PATTERNS);
        String[] urlPatternsArr = urlPatterns.split(Consts.FILTER_URL_PATTERNS_SEPARATOR);
        return buildSimpleFilter(new TraceIdDefaultHttpFilter(), "traceIdDefaultHttpFilter", true, -99, urlPatternsArr);
    }

    /**
     * traceId的mdc方式过滤器
     */
    @Bean
    @DependsOn("loggerContext")
    @ConditionalOnExpression("'mdc'.equals('${unicLogger.traceIdFilterMode}')")
    public FilterRegistrationBean traceIdMdcHttpFilter() {
        String urlPatterns = LoggerContext.getValue(Consts.HTTP_FILTER_URL_PATTERNS);
        String[] urlPatternsArr = urlPatterns.split(Consts.FILTER_URL_PATTERNS_SEPARATOR);
        return buildSimpleFilter(new TraceIdMdcHttpFilter(), "traceIdMdcHttpFilter", true, -99, urlPatternsArr);
    }

    /**
     * 构建简单的filter
     *
     * @param filter      具体的filter
     * @param urlPatterns 拦截路径
     * @param filterName  过滤器名称
     * @param enable      是否自动注册
     * @param order       顺序
     * @return FilterRegistrationBean
     */
    public static FilterRegistrationBean buildSimpleFilter(Filter filter, String filterName, boolean enable, int order, String... urlPatterns) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPatterns);
        registration.setName(filterName);
        registration.setEnabled(enable);
        registration.setOrder(order);
        LOGGER.info("{} register complete, urlPatterns: [{}]", filterName, urlPatterns);
        return registration;
    }

}
