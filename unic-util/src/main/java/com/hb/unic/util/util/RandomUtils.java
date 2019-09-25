package com.hb.unic.util.util;

/**
 * 随机数工具类
 */
public class RandomUtils {

    /**
     * 获取固定范围内的随机数
     *
     * @param start 最小值
     * @param end   最大值
     * @return 随机数
     */
    public static int getRandomBetween(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

}
