package com.hb.unic.rbac.config.handler;

import com.hb.unic.base.common.Result;
import com.hb.unic.common.util.LogUtils;
import com.hb.unic.common.util.ServletUtils;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理器
 *
 * @version v0.1, 2021/4/18 0:03, create by huangbiao.
 */
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
        throws IOException {
        log.info("权限不足={}", LogUtils.getStackTrace(e));
        ServletUtils.writeJson(response, Result.fail(RbacResultCode.ACCESS_DENIED));
    }

}
