package com.hb.unic.base.aspect;

import com.google.common.base.Stopwatch;
import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.util.util.StrUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * controller日志打印
 *
 * @version v0.1, 2020/9/4 10:34, create by huangbiao.
 */
@Aspect
@Component
public class InOutLogAspect {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InOutLogAspect.class);

    /**
     * 入参出参日志打印
     *
     * @param joinPoint 参数
     * @return Object
     */
    @Around("@annotation(com.hb.unic.base.annotation.InOutLog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        InOutLog inOutLog = signature.getMethod().getAnnotation(InOutLog.class);
        String value = inOutLog.value();
        String baseLog = StrUtils.joint("[", className, "-", methodName, "-", value, "]");
        LOGGER.info("{}入参={}", baseLog, args);
        Object proceed = joinPoint.proceed(args);
        LOGGER.info("{}出参={}, 耗时={}毫秒", baseLog, proceed, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return proceed;
    }

}
    