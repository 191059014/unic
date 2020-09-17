package com.hb.unic.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 入参出参日志
 *
 * @version v0.1, 2020/9/17 11:08, create by huangbiao.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InOutLog {

    /**
     * 日志描述
     *
     * @return 日志描述
     */
    String value();

}

    