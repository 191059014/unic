package com.hb.unic.base.util;

/**
 * @author guoll
 * @date 2020/9/20
 */
public class LogHelper {

    /**
     * 获取标准日志
     *
     * @param desc 描述
     * @return 日志
     */
    public static String getBaseLog(String desc) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[2].getClassName();
        return new StringBuilder()
                .append("[")
                .append(className.substring(className.lastIndexOf(".") + 1))
                .append("-")
                .append(stackTrace[2].getMethodName())
                .append("-")
                .append(desc)
                .append("]")
                .toString();
    }

}
