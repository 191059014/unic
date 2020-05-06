package com.hb.unic.logger.filter;

import com.hb.unic.logger.util.TraceIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * traceId的过滤器
 *
 * @author Mr.huang
 * @since 2020/4/21 9:07
 */
public class TraceIdFilter implements Filter {

    /**
     * org.slf4j.Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 初始化traceId
            String traceId = servletRequest.getParameter("traceId");
            if (traceId == null || "".equals(traceId)) {
                TraceIdUtils.setTraceId(TraceIdUtils.generateTraceId());
            } else {
                TraceIdUtils.setTraceId(traceId);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 销毁traceId
            TraceIdUtils.removeTraceId();
        }
    }

}
