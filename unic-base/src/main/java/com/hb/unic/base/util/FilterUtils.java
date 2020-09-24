package com.hb.unic.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

/**
 * 过滤器工具类
 *
 * @author Mr.huang
 * @since 2020/5/6 11:12
 */
public class FilterUtils {

    /**
     * org.slf4j.Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUtils.class);

    /**
     * 构建简单的filter
     *
     * @param filter      具体的filter
     * @param filterName  过滤器名称
     * @param enable      是否自动注册
     * @param order       顺序
     * @param urlPatterns 拦截路径
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
