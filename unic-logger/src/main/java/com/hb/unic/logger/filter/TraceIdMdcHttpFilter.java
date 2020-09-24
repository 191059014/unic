package com.hb.unic.logger.filter;

import com.hb.unic.logger.common.Consts;
import com.hb.unic.logger.util.TraceIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * traceId的过滤器，mdc方式
 *
 * @author Mr.huang
 * @since 2020/4/21 9:07
 */
public class TraceIdMdcHttpFilter implements Filter {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdMdcHttpFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String traceId = httpServletRequest.getHeader(Consts.TRACE_ID);
            if (traceId == null) {
                traceId = TraceIdUtils.generateTraceId();
            }
            MDC.put(Consts.TRACE_ID, traceId);
            LOGGER.debug("doFilter, traceId={}", traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TraceIdUtils.removeTraceId();
        }
    }

}
