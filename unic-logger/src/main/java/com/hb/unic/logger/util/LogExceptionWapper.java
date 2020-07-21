package com.hb.unic.logger.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ========== 日志工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.LogExceptionWapper.java, v1.0
 * @date 2019年06月14日 11时07分
 */
public class LogExceptionWapper {

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
