package com.hb.unic.base.aspect;

import com.alibaba.fastjson.JSON;
import com.hb.unic.base.annotation.InOutLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 日志切面
 *
 * @version v0.1, 2021/8/24 21:06, create by huangbiao.
 */
@Aspect
@Component
public class LogAspect {

    /**
     * the constant log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切面
     */
    @Pointcut("@annotation(com.hb.unic.base.annotation.InOutLog)")
    public void logPointCut() {}

    /**
     * 周围通知
     *
     * @param point
     *            调用参数
     * @return Object 方法调用结果
     * @throws Throwable
     *             异常
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        MethodSignature signature = (MethodSignature)point.getSignature();
        InOutLog inOutLog = signature.getMethod().getAnnotation(InOutLog.class);
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        Object[] args = point.getArgs();
        if (inOutLog.printInLog()) {
            LOGGER.info(getLog(className, methodName, inOutLog.value(), "Input", args));
        }
        Object result = point.proceed();
        if (inOutLog.printOutLog()) {
            sw.stop();
            long useTime = sw.getTotalTimeMillis();
            LOGGER.info("{}, cost={}ms", getLog(className, methodName, inOutLog.value(), "Output", result), useTime);
        }
        return result;
    }

    /**
     * 组装日志
     *
     * @param className
     *            类名
     * @param methodName
     *            方法名
     * @param logDesc
     *            日志描述
     * @param mark
     *            标记
     * @param param
     *            参数
     * @return 日志
     */
    private String getLog(String className, String methodName, String logDesc, String mark, Object param) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(className).append("-").append(methodName).append("-").append(logDesc).append("]")
            .append(mark).append("=").append(JSON.toJSONString(param));
        return sb.toString();
    }

}
