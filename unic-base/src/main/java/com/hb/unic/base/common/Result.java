package com.hb.unic.base.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.common.standard.IErrorCode;
import lombok.Data;

/**
 * 公用返回数据模型
 *
 * @author Mr.huang
 * @since 2020/4/20 16:04
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 构造方法
     *
     * @param code
     *            响应码
     * @param msg
     *            相应信息
     * @param data
     *            业务数据
     */
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 生成响应对象
     *
     * @return 完整返回对象
     */
    public static <T> Result<T> success() {
        return of(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), null);
    }

    /**
     * 生成响应对象
     *
     * @param data
     *            业务数据
     * @return 完整返回对象
     */
    public static <T> Result<T> success(T data) {
        return of(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    /**
     * 生成响应对象
     *
     * @param errorCode
     *            错误码
     * @return 完整返回对象
     */
    public static <T> Result<T> fail(IErrorCode errorCode) {
        return of(errorCode.getCode(), errorCode.getMsg(), null);
    }

    /**
     * 生成响应对象
     *
     * @param errorCode
     *            错误码
     * @param data
     *            业务数据
     * @param <T>
     *            数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> fail(IErrorCode errorCode, T data) {
        return of(errorCode.getCode(), errorCode.getMsg(), data);
    }

    /**
     * 生成响应对象
     *
     * @param code
     *            返回码
     * @param msg
     *            返回信息
     * @param <T>
     *            数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> fail(String code, String msg) {
        return of(code, msg, null);
    }

    /**
     * 生成响应对象
     *
     * @param errorCode
     *            返回码
     * @param data
     *            业务数据
     * @param <T>
     *            数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> of(IErrorCode errorCode, T data) {
        return of(errorCode.getCode(), errorCode.getMsg(), data);
    }

    /**
     * 生成响应对象
     *
     * @param code
     *            返回码
     * @param msg
     *            返回信息
     * @param data
     *            业务数据
     * @param <T>
     *            数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> of(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

}
