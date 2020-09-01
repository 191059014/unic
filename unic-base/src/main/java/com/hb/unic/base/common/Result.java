package com.hb.unic.base.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.util.util.EnumUtils;

import java.io.Serializable;
import java.util.Optional;

/**
 * 公用返回数据模型
 *
 * @author Mr.huang
 * @since 2020/4/20 16:04
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4226532525009161450L;
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
     * @param code 响应码
     * @param msg  相应信息
     * @param data 业务数据
     */
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 生成ResponseData
     *
     * @param enumObj 返回码枚举对象
     * @return 完整返回对象
     */
    public static Result of(Enum enumObj) {
        return of(getCode(enumObj), getMsg(enumObj), null);
    }

    /**
     * 生成ResponseData
     *
     * @param code 返回码
     * @param msg  返回信息
     * @param <T>  数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> of(String code, String msg) {
        return of(code, msg, null);
    }

    /**
     * 生成ResponseData
     *
     * @param enumObj 返回码枚举对象
     * @param data    业务数据
     * @param <T>     数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> of(Enum enumObj, T data) {
        return of(getCode(enumObj), getMsg(enumObj), data);
    }

    /**
     * 生成ResponseData
     *
     * @param code 返回码
     * @param msg  返回信息
     * @param data 业务数据
     * @param <T>  数据类型
     * @return 完整返回对象
     */
    public static <T> Result<T> of(String code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 根据枚举对象获取code
     *
     * @param enumObj 枚举对象
     * @return code值
     */
    public static String getCode(Enum enumObj) {
        Object codeObj = EnumUtils.get(enumObj, EnumUtils.KeysEnum.code.name());
        return Optional.ofNullable(codeObj).orElse("").toString();
    }

    /**
     * 根据枚举对象获取msg
     *
     * @param enumObj 枚举对象
     * @return msg值
     */
    public static String getMsg(Enum enumObj) {
        Object msgObj = EnumUtils.get(enumObj, EnumUtils.KeysEnum.msg.name());
        return Optional.ofNullable(msgObj).orElse("").toString();
    }

}
