package com.hb.unic.util.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfTableColumn {

    /**
     * 列明
     */
    String value();

    /**
     * 是否是ID
     */
    boolean id() default false;

    /**
     * 长度
     */
    int length() default 255;

    /**
     * 默认值
     */
    String defaultValue() default "";

    /**
     * 注释
     */
    String comment() default "";

}
