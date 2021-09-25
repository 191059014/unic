package com.hb.unic.rbac.config.handler;

import com.hb.unic.base.common.Result;
import com.hb.unic.common.util.ServletUtils;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理器
 *
 * @author Mr.Huang
 * @version v0.1, 2020/6/2 9:10, create by huangbiao.
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        log.info("注销成功");
        ServletUtils.writeJson(response, Result.fail(RbacResultCode.LOGOUT_SUCCESS));
    }
}
