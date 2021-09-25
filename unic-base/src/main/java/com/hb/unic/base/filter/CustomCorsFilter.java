package com.hb.unic.base.filter;

import com.hb.unic.base.BaseContext;
import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ErrorCode;
import com.hb.unic.common.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * ========== 跨域过滤器 ==========
 *
 * @author Mr.huang
 * @version v1.0
 * @date 2020年09月22日 00时10分
 */
public class CustomCorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        String allowOrigins = BaseContext.env().getProperty("cros.allowOrigins");
        if (StringUtils.isNotBlank(allowOrigins)) {
            String[] allowOriginArr = allowOrigins.split(",");
            List<String> list = Arrays.asList(allowOriginArr);
            if (!list.contains(request.getRemoteHost())) {
                ServletUtils.writeJson(response, Result.fail(ErrorCode.INVALID_ORIGIN));
                return;
            }
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE,PATCH,HEAD");
        response.setHeader("Access-Control-Allow-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

}