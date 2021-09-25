package com.hb.unic.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志辅助
 *
 * @version v0.1, 2020/7/24 15:07, create by huangbiao.
 */
public class LogUtils {

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

    /**
     * ########## 获取异常堆栈信息 ##########
     *
     * @param throwable 异常超类
     * @return 堆栈信息
     */
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
