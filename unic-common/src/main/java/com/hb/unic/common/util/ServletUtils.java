package com.hb.unic.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * request和response的工具类
 *
 * @version v0.1, 2020/7/30 11:02, create by huangbiao.
 */
public class ServletUtils {

    /**
     * 获取请求参数集合，适用于get请求、表单方式application/x- www-form-urlencoded
     *
     * @return 参数集合
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        Enumeration e = request.getParameterNames();
        // 循环获取参数
        while (e.hasMoreElements()) {
            String paramterName = e.nextElement().toString();
            param.put(paramterName, request.getParameter(paramterName));
        }
        return param;
    }

    /**
     * 响应给客户端的信息
     *
     * @param response
     *            HttpServletResponse对象
     * @param obj
     *            任意对象
     */
    public static void writeJson(HttpServletResponse response, Object obj) throws IOException {
        // 设置编码格式
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(obj));
    }

    /**
     * 匹配uri
     *
     * @param path
     *            需要匹配的路径
     * @param uriPatterns
     *            匹配路径集合
     */
    public static boolean matchUri(String path, String... uriPatterns) {
        for (String uriPattern : uriPatterns) {
            AntPathMatcher matcher = new AntPathMatcher();
            if (matcher.match(uriPattern, path)) {
                return true;
            }
        }
        return false;
    }

}
