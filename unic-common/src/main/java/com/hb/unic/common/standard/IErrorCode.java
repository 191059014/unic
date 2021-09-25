package com.hb.unic.common.standard;

/**
 * 标准错误码接口
 *
 * @version v0.1, 2021/8/22 18:37, create by huangbiao.
 */
public interface IErrorCode {

    /**
     * 获取code
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 获取msg
     *
     * @return 错误信息
     */
    String getMsg();

}

    