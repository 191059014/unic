package com.hb.unic.base.common;

import com.hb.unic.common.standard.IErrorCode;

/**
 * 返回码
 *
 * @version v0.1, 2021/8/22 18:42, create by huangbiao.
 */
public enum ResultCode implements IErrorCode {

    SUCCESS("10000", "成功"),
    ERROR("10001", "系统异常，请稍后再试！"),
    FAIL("10002", "操作失败"),
    NO_DATA("10003", "无数据"),
    PARAM_ILLEGAL("10004", "参数非法"),
    PAGE_PARAM_ERROR("10005", "分页参数错误"),
    RECORD_REPEAT("10008", "已存在唯一性相同的记录"),
    ;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    ResultCode(String code, String msg) {
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
