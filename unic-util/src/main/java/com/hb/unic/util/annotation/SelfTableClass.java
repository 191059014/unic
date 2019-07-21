package com.hb.unic.util.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfTableClass {

    /**
     * 表名
     */
    String value();

    /**
     * 注释
     */
    String comment() default "";

}
