package com.hb.unic.rbac.common.enums;

import com.hb.unic.common.standard.IErrorCode;

/**
 * 返回码，1000-1100
 *
 * @version v0.1, 2021/8/22 18:42, create by huangbiao.
 */
public enum RbacResultCode implements IErrorCode {

    USERNAME_NULL("1001", "用户名为空"),
    PASSWORD_NULL("1002", "密码为空"),
    LOGIN_SUCCESS("1003", "登录成功"),
    LOGIN_FAIL("1004", "登录失败"),
    LOGOUT_SUCCESS("1005", "注销成功"),
    LOGOUT_FAIL("1006", "注销失败"),
    ACCESS_DENIED("1007", "权限不足"),
    NOT_LOGIN("1008", "当前处于未登录状态，禁止访问"),
    ACCOUNT_EXPIRED("1009", "账号过期"),
    PASSWORD_ERROR("1010", "密码错误"),
    PASSWORD_EXPIRED("1011", "密码过期"),
    ACCOUNT_DISABLED("1012", "账号不可用"),
    ACCOUNT_LOCKED("1013", "账号锁定"),
    ACCOUNT_NOT_EXIST("1014", "用户不存在"),
    TOKEN_NULL("1015", "token为空"),
    TOKEN_ILLEGAL("1016", "token非法"),
    TOKEN_EXPIRED("1017", "token过期"),
    IP_CHANGE("1018", "您和上次登录IP不一致，为保证安全，请重新登录"),
    OLD_PASSWORD_ERROR("1019", "旧密码错误"),
    ;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    RbacResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
