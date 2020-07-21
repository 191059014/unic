package com.hb.unic.base.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.util.util.JsonUtils;

import java.io.Serializable;

/**
 * 公用返回数据模型
 *
 * @author Mr.huang
 * @since 2020/4/20 16:04
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseData<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4226532525009161450L;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 总条数
     */
    private Integer count;
    /**
     * 业务数据
     */
    private T data;

    /**
     * 构造方法
     *
     * @param code  响应码
     * @param msg   相应信息
     * @param count 总条数
     * @param data  业务数据
     */
    public ResponseData(int code, String msg, Integer count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCount() {
        return count;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
