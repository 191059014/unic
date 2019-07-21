package com.hb.unic.util.tool;

import java.util.UUID;

/**
 * ========== id生成器 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.tool.IdWorker.java, v1.0
 * @date 2019年07月22日 01时00分
 */
public class IdWorker {

    /**
     * ########## 获取单例 ##########
     *
     * @return IdWorker对象
     */
    public static IdWorker getInstance(){
        return IdWorkerHolder.instance;
    }

    /**
     * ########## 静态内部类 ##########
     */
    private static class IdWorkerHolder{
        public static final IdWorker instance = new IdWorker();
    }

    /**
     * ########## 获取uuid ##########
     *
     * @return uuid
     */
    public String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * ########## 获取uuid去掉'-'后的字符串 ##########
     *
     * @return uuid
     */
    public String uuidShort(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
