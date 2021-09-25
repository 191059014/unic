package com.hb.unic.common.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

/**
 * filter工具类
 *
 * @version v0.1, 2021/9/3 23:45, create by huangbiao.
 */
@Slf4j
public class FilterUtils {

    /**
     * the constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUtils.class);

    /**
     * 构建简单的filter
     *
     * @param filter
     *            具体的filter
     * @param urlPatterns
     *            拦截路径
     * @param filterName
     *            过滤器名称
     * @param order
     *            顺序
     * @return FilterRegistrationBean
     */
    public static FilterRegistrationBean build(Filter filter, String filterName, int order, String... urlPatterns) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(urlPatterns);
        registration.setName(filterName);
        registration.setEnabled(true);
        registration.setOrder(order);
        LOGGER.info("{} register complete, urlPatterns: [{}], order: {}", filter.getClass().getSimpleName(),
            urlPatterns, order);
        return registration;
    }

}
