package com.hb.unic.util.tool;

import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.StringUtils;

import java.util.UUID;

/**
 * ========== id生成器 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.tool.IdWorker.java, v1.0
 * @date 2019年07月22日 01时00分
 */
public class IdWorker {

    private static long sequence = 0L;

    private long maxSequence = 999999L;

    private int sequenceLength = 6;

    private static String lastTimeStamp = "";

    /**
     * ########## 获取单例 ##########
     *
     * @return IdWorker对象
     */
    public static IdWorker getInstance() {
        return IdWorkerHolder.instance;
    }

    /**
     * ########## 静态内部类 ##########
     */
    private static class IdWorkerHolder {
        public static final IdWorker instance = new IdWorker();
    }

    /**
     * ########## 生成ID，支持每秒最多999999个 ##########
     *
     * @return ID
     */
    public synchronized String nextId() {
        String currentTimeStamp = DateUtils.date2str(DateUtils.getCurrentDate(), DateUtils.FORMAT_YMDHMS);
        sequence++;
        if (sequence > maxSequence) {
            sequence = 1;
        }
        if (!currentTimeStamp.equals(lastTimeStamp)) {
            sequence = 1;
        }
        lastTimeStamp = currentTimeStamp;
        String sequenceId = StringUtils.fillZero(sequence + "", sequenceLength);
        return new StringBuilder(currentTimeStamp).append(sequenceId).toString();
    }

    /**
     * ########## 获取uuid ##########
     *
     * @return uuid
     */
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * ########## 获取uuid去掉'-'后的字符串 ##########
     *
     * @return uuid
     */
    public String uuidShort() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
