package com.hb.unic.rbac.config.handler;

import com.hb.unic.base.common.Result;
import com.hb.unic.common.standard.IErrorCode;
import com.hb.unic.common.util.LogUtils;
import com.hb.unic.common.util.ServletUtils;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败处理器
 *
 * @author Mr.Huang
 * @version v0.1, 2020/6/2 9:10, create by huangbiao.
 */
@Slf4j
@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException e) throws IOException {
        log.info("登陆失败={}", LogUtils.getStackTrace(e));
        IErrorCode errorCode = null;
        if (e instanceof UsernameNotFoundException) {
            // 用户不存在
            errorCode = RbacResultCode.ACCOUNT_NOT_EXIST;
        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            errorCode = RbacResultCode.PASSWORD_ERROR;
        } else if (e instanceof AccountExpiredException) {
            // 账号过期
            errorCode = RbacResultCode.ACCOUNT_EXPIRED;
        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            errorCode = RbacResultCode.PASSWORD_EXPIRED;
        } else if (e instanceof DisabledException) {
            // 账号不可用
            errorCode = RbacResultCode.ACCOUNT_DISABLED;
        } else if (e instanceof LockedException) {
            // 账号锁定
            errorCode = RbacResultCode.ACCOUNT_LOCKED;
        } else {
            // 其他错误
            errorCode = RbacResultCode.LOGIN_FAIL;
        }
        ServletUtils.writeJson(response, Result.fail(errorCode));
    }

}
