package com.hb.unic.util.helper;

import java.util.UUID;

/**
 * ========== id生成器 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.helper.SimpleIdHelper.java, v1.0
 * @date 2019年07月22日 01时00分
 */
public class SimpleIdHelper {

    /**
     * ########## 获取uuid ##########
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * ########## 获取uuid去掉'-'后的字符串 ##########
     *
     * @return uuid
     */
    public static String uuidShort() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
