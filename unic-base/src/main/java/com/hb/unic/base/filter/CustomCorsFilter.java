package com.hb.unic.base.filter;

import com.hb.unic.base.util.LogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ========== 跨域过滤器 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.filter.CorsFilter.java, v1.0
 * @date 2020年09月22日 00时10分
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CustomCorsFilter implements Filter {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCorsFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String baseLog = LogHelper.getBaseLog("跨域过滤器");
        LOGGER.debug("{}开始", baseLog);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE,PATCH,HEAD");
        response.setHeader("Access-Control-Allow-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            LOGGER.debug("{}options请求，直接返回成功", baseLog);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        LOGGER.debug("{}结束", baseLog);
    }

}