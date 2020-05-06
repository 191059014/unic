package com.hb.unic.logger.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ========== 日志工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.LogUtils.java, v1.0
 * @date 2019年06月14日 11时07分
 */
public class LogUtils {

    /**
     * ########## 获取异常堆栈信息 ##########
     *
     * @param throwable 异常超类
     * @return 堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
