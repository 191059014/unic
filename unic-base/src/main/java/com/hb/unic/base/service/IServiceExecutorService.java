package com.hb.unic.base.service;

import org.springframework.util.concurrent.ListenableFuture;

/**
 * ========== 异步任务处理接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.service.IServiceExecutorService.java, v1.0
 * @date 2019年09月05日 14时16分
 */
public interface IServiceExecutorService {

    /**
     * 基于线程池的异步调用
     *
     * @param serviceName     the service name
     * @param methodName      the method name
     * @param methodParameter the method parameter
     */
    void execute(String serviceName, String methodName, Object[] methodParameter);

    /**
     * 基于线程池的异步调用
     *
     * @param service         the service
     * @param methodName      the method name
     * @param methodParameter the method parameter
     */
    void execute(Object service, String methodName, Object[] methodParameter);

    /**
     * 等待结果的异步调用
     *
     * @param serviceName     the service name
     * @param methodName      the method name
     * @param methodParameter the method parameter
     * @return listenable future
     */
    ListenableFuture<Object> executeFuture(String serviceName, String methodName, Object[] methodParameter);

    /**
     * 等待结果的异步调用
     *
     * @param service         the service
     * @param methodName      the method name
     * @param methodParameter the method parameter
     * @return listenable future
     */
    ListenableFuture<Object> executeFuture(Object service, String methodName, Object[] methodParameter);

    /**
     * 同步调用
     *
     * @param service         the service
     * @param methodName      the method name
     * @param methodParameter the method parameter
     * @return object
     */
    Object invoke(Object service, String methodName, Object[] methodParameter);

    /**
     * 同步调用
     *
     * @param serviceName     the service name
     * @param methodName      the method name
     * @param methodParameter the method parameter
     * @return object
     */
    Object invoke(String serviceName, String methodName, Object[] methodParameter);

}
