package com.hb.unic.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 出参入参日志
 *
 * @version v0.1, 2021/8/24 21:13, create by huangbiao.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InOutLog {

    /**
     * 描述
     *
     * @return 描述
     */
    String value() default "";

    /**
     * 打印入参
     * 
     * @return true为是
     */
    boolean printInLog() default true;

    /**
     * 打印出参
     * 
     * @return true为是
     */
    boolean printOutLog() default true;

}
