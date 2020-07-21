package com.hb.unic.logger.filter;

import com.hb.unic.logger.common.Consts;
import com.hb.unic.logger.util.TraceIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * traceId的过滤器
 *
 * @author Mr.huang
 * @since 2020/4/21 9:07
 */
public class TraceIdHttpFilter implements Filter {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdHttpFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 初始化traceId
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String traceId = httpServletRequest.getHeader(Consts.TRACE_ID);
            if (traceId == null) {
                traceId = TraceIdUtils.generateTraceId();
                LOGGER.debug("http请求头里没有traceId，生成traceId：{}", traceId);
            }
            TraceIdUtils.setTraceId(traceId);
            LOGGER.debug("设置线程关联的traceId：{}", traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // 销毁traceId
            TraceIdUtils.removeTraceId();
            LOGGER.debug("销毁traceId");
        }
    }

}
