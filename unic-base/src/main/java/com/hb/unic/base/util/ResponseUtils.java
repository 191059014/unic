package com.hb.unic.base.util;

import com.hb.unic.base.common.ResponseData;
import com.hb.unic.util.util.EnumUtils;

import java.util.Optional;

/**
 * 针对ResponseData的封装
 *
 * @author Mr.huang
 * @since 2020/4/20 16:18
 */
public class ResponseUtils {

    /**
     * 生成ResponseData
     *
     * @param enumObj 返回码枚举对象
     * @return 完整返回对象
     */
    public static ResponseData generateResponseData(Enum enumObj) {
        return generateResponseData(getCode(enumObj), getMsg(enumObj), null);
    }

    /**
     * 生成ResponseData
     *
     * @param code 返回码
     * @param msg  返回信息
     * @param <T>  数据类型
     * @return 完整返回对象
     */
    public static <T> ResponseData<T> generateResponseData(String code, String msg) {
        return generateResponseData(code, msg, null);
    }

    /**
     * 生成ResponseData
     *
     * @param enumObj 返回码枚举对象
     * @param data    业务数据
     * @param <T>     数据类型
     * @return 完整返回对象
     */
    public static <T> ResponseData<T> generateResponseData(Enum enumObj, T data) {
        return generateResponseData(getCode(enumObj), getMsg(enumObj), data);
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
    public static <T> ResponseData<T> generateResponseData(String code, String msg, T data) {
        return generateResponseData(code, msg, data);
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
