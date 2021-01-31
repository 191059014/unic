package com.hb.unic.logger;

/**
 * ========== 自定义logger ==========
 *
 * @author Mr.huang
 * @version v1.0
 * @date 2019年07月16日 10时20分
 */
public class LoggerFactory {

    /**
     * ########## 通过类获取日志 ##########
     *
     * @param clazz 类
     * @return 自定义的日志对象
     */
    public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }

    /**
     * ########## 通过名字获取日志 ##########
     *
     * @param name 日志名字
     * @return 自定义的日志对象
     */
    public static Logger getLogger(String name) {
        return new Logger(name);
    }

}
