package com.hb.unic.common.util;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtils {

    /**
     * 获取固定范围内的随机数
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static int getRandomBetween(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * 获取指定位数的随机数字
     *
     * @param length 长度
     * @return 字符串
     */
    public static String getRandomOfLength(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
