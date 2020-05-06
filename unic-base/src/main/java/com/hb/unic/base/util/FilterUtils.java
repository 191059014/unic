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
     * @param urlPatterns 拦截路径
     * @param filterName  过滤器名称
     * @param enable      是否自动注册
     * @param order       顺序
     * @return FilterRegistrationBean
     */
    public static FilterRegistrationBean buildSimpleFilter(Filter filter, String urlPatterns, String filterName, boolean enable, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPatterns);
        registration.setName(filterName);
        registration.setEnabled(enable);
        registration.setOrder(order);
        LOGGER.info("{} register success", filterName);
        return registration;
    }

}
