package com.hb.unic.base.controller;

import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ErrorCode;
import com.hb.unic.common.standard.BusinessException;
import com.hb.unic.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller父类
 *
 * @version v0.1, 2021/8/22 22:54, create by huangbiao.
 */
@Slf4j
public class BaseController {

    /**
     * 请求
     */
    @Resource
    protected HttpServletRequest request;

    /**
     * 响应
     */
    @Resource
    protected HttpServletResponse response;

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result exception(BusinessException e) {
        String baseLog = LogUtils.getBaseLog("业务异常");
        log.error("{}\n{}", baseLog, LogUtils.getStackTrace(e));
        return Result.fail(e.getKey(), e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        String baseLog = LogUtils.getBaseLog("系统异常");
        log.error("{}\n{}", baseLog, LogUtils.getStackTrace(e));
        return Result.fail(ErrorCode.ERROR);
    }

}
