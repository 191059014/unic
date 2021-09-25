package com.hb.unic.rbac.config.aspect;

import com.hb.unic.base.model.IBaseDO;
import com.hb.unic.base.model.ITenantDO;
import com.hb.unic.rbac.RbacContext;
import com.hb.unic.rbac.common.util.RbacUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 基础数据库字段处理切面
 *
 * @version v0.1, 2021/8/24 21:06, create by huangbiao.
 */
@Aspect
@Component
@Slf4j
public class BasePropertyAspect {

    /**
     * 所有的方法
     */
    @Pointcut("execution(public * com.hb.platform..*.*Mapper.*(..))")
    public void allPointcut() {}

    /**
     * 所有插入的方法
     */
    @Pointcut("execution(public * com.hb.platform..*.*Mapper.insert*(..))")
    public void allInsertPointcut() {}

    /**
     * 所有更新的方法
     */
    @Pointcut("execution(public * com.hb.platform..*.*Mapper.update*(..))")
    public void allUpdatePointcut() {}

    /**
     * 所有删除的方法
     */
    @Pointcut("execution(public * com.hb.platform..*.*Mapper.delete*(..))")
    public void allDeletePointcut() {}

    /**
     * 前通知
     *
     * @param joinPoint
     *            调用参数
     */
    @Before("allPointcut()")
    private void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ITenantDO) {
                ITenantDO tenantDO = (ITenantDO)arg;
                if (!RbacUtils.isSuperAdmin(RbacContext.getCurrentUserId())) {
                    // 处理多租户ID
                    // 超级管理员，不处理租户ID，增删改查都不用处理
                    // 非超级管理员，如果多租户ID为空，则赋值为当前用户的租户ID，增删改查都需要处理
                    if (tenantDO.getTenantId() == null) {
                        tenantDO.setTenantId(RbacContext.getCurrentTenantId());
                    }
                }
            }
        }
    }

    /**
     * 前通知
     *
     * @param joinPoint
     *            调用参数
     */
    @Before("allInsertPointcut()")
    private void beforeInsert(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof IBaseDO) {
                // 处理创建人，更新人
                IBaseDO baseDO = (IBaseDO)arg;
                if (StringUtils.isBlank(baseDO.getCreateBy())) {
                    baseDO.setCreateBy(RbacContext.getCurrentUser().getUserName());
                }
                if (StringUtils.isBlank(baseDO.getUpdateBy())) {
                    baseDO.setUpdateBy(RbacContext.getCurrentUser().getUserName());
                }
            }
        }
    }

    /**
     * 前通知
     *
     * @param joinPoint
     *            调用参数
     */
    @Before("allUpdatePointcut()")
    private void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof IBaseDO) {
                // 处理更新人
                IBaseDO baseDO = (IBaseDO)arg;
                if (StringUtils.isBlank(baseDO.getUpdateBy())) {
                    baseDO.setUpdateBy(RbacContext.getCurrentUser().getUserName());
                }
            }
        }
    }

    /**
     * 前通知
     *
     * @param joinPoint
     *            调用参数
     */
    @Before("allDeletePointcut()")
    private void beforeDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof IBaseDO) {
                // 处理创建人，更新人
                IBaseDO baseDO = (IBaseDO)arg;
                if (StringUtils.isBlank(baseDO.getUpdateBy())) {
                    baseDO.setUpdateBy(RbacContext.getCurrentUser().getUserName());
                }
            }
        }
    }

}
