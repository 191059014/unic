package com.hb.unic.rbac.config.handler;

import com.hb.unic.base.common.Result;
import com.hb.unic.common.util.LogUtils;
import com.hb.unic.common.util.ServletUtils;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录的情况下，访问资源
 *
 * @version v0.1, 2021/4/18 0:09, create by huangbiao.
 */
@Slf4j
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
        throws IOException {
        log.info("当前处于未登录状态，禁止访问={}", LogUtils.getStackTrace(e));
        ServletUtils.writeJson(response, Result.fail(RbacResultCode.NOT_LOGIN));
    }

}
