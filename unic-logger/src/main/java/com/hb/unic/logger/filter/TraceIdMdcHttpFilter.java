package com.hb.unic.logger.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * traceId的过滤器，mdc方式
 *
 * @author Mr.huang
 * @since 2020/4/21 9:07
 */
@Slf4j
public class TraceIdMdcHttpFilter extends OncePerRequestFilter {

    /**
     * traceId
     */
    private static final String TRACE_ID = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        try {
            String traceId = request.getHeader(TRACE_ID);
            if (traceId == null) {
                traceId = UUID.randomUUID().toString().replaceAll("-", "");
            }
            MDC.put(TRACE_ID, traceId);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

}
