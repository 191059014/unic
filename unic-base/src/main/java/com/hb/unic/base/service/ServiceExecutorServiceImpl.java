package com.hb.unic.base.service;

import com.hb.unic.base.container.BaseServiceLocator;
import com.hb.unic.base.exception.StandardRuntimeException;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.apache.commons.beanutils.MethodUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * ========== Service之间同步/异步调用 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.base.service.ServiceExecutorServiceImpl.java, v1.0
 * @date 2019年09月05日 14时24分
 */
@Component
public class ServiceExecutorServiceImpl implements IServiceExecutorService {

    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Async
    public void execute(String serviceName, String methodName, Object[] methodParameter) {
        Object service = BaseServiceLocator.getBean(serviceName);
        if (service != null) {
            execute(service, methodName, methodParameter);
        } else {
            logger.error("Service Name:{} is not exist error!", serviceName);
        }
    }

    @Override
    @Async
    public void execute(Object service, String methodName, Object[] methodParameter) {
        if (service != null) {
            try {
                Object r = MethodUtils.invokeMethod(service, methodName, methodParameter);
            } catch (Exception e) {
                logger.error("Service:{} execute Failure!", service);
                throw new StandardRuntimeException("ServiceExecuteFailure", service + "." + methodName);
            }
        } else {
            logger.error("Service Object is not exist error!");
        }
    }

    @Override
    @Async
    public ListenableFuture<Object> executeFuture(String serviceName, String methodName, Object[] methodParameter) {
        Object service = BaseServiceLocator.getBean(serviceName);
        if (service != null) {
            try {
                Object r = MethodUtils.invokeMethod(service, methodName, methodParameter);
                return new AsyncResult<Object>(r);
            } catch (Exception e) {
                logger.error("serviceName:{},methodName:{} executeFuture error!", serviceName, methodName);
                throw new StandardRuntimeException("ServiceExecuteFutureFailure", serviceName + "." + methodName);
            }
        } else {
            logger.error("Service Name:{} is not exist error!", serviceName);
        }

        return null;
    }

    @Override
    @Async
    public ListenableFuture<Object> executeFuture(Object service, String methodName, Object[] methodParameter) {
        if (service != null) {
            try {
                Object r = MethodUtils.invokeMethod(service, methodName, methodParameter);
                return new AsyncResult<Object>(r);
            } catch (Exception e) {
                logger.error("service:{},methodName:{} executeFuture error!", service, methodName);
                throw new StandardRuntimeException("ServiceExecuteFutureFailure", service + "." + methodName);
            }
        } else {
            logger.error("Service Object is not exist error!");
        }

        return null;
    }

    @Override
    public Object invoke(String serviceName, String methodName, Object[] methodParameter) {
        Object service = BaseServiceLocator.getBean(serviceName);
        if (service != null) {
            try {
                Object r = invoke(service, methodName, methodParameter);
                return r;
            } catch (Exception e) {
                logger.error("serviceName:{},methodName:{} invoke Failure!", serviceName, methodName);
                throw new StandardRuntimeException("ServiceInvokeFailure", serviceName + "." + methodName);
            }
        } else {
            logger.error("Service Name:" + serviceName + " is not exist error!");
        }

        return null;
    }

    @Override
    public Object invoke(Object service, String methodName, Object[] methodParameter) {
        if (service != null) {
            try {
                Object r = MethodUtils.invokeMethod(service, methodName, methodParameter);
                return r;
            } catch (Exception e) {
                logger.error("service:{},methodName:{} invoke Failure!", service, methodName);
                throw new StandardRuntimeException("ServiceInvokeFailure", service + "." + methodName);
            }
        } else {
            logger.error("Service Object is not exist error!");
        }

        return null;
    }

}
