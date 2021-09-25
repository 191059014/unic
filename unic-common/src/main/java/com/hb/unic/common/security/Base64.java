package com.hb.unic.common.security;

import lombok.extern.slf4j.Slf4j;

/**
 * base64加密算法
 *
 * @author Mr.Huang
 * @version v0.1, 2020/5/29 9:29, create by huangbiao.
 */
@Slf4j
public class Base64 {

    /**
     * 加密
     * 
     * @param message
     *            原字符串
     * @return 结果
     */
    public static String encode(String message) {
        try {
            return new String(java.util.Base64.getEncoder().encode(message.getBytes("UTF-8")), "UTF-8");
        } catch (Exception e) {
            log.error("Base64加密异常, message={}", message);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     * 
     * @param encrypt
     *            加密后的字符串
     * @return 结果
     */
    public static String decode(String encrypt) {
        try {
            return new String(java.util.Base64.getDecoder().decode(encrypt.getBytes("UTF-8")), "UTF-8");
        } catch (Exception e) {
            log.error("Base64解密异常, message={}", encrypt);
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验
     * 
     * @param message
     *            原字符串
     * @param encrypt
     *            加密后的字符串
     * @return 结果
     */
    public static boolean verify(String message, String encrypt) {
        return encrypt != null && encrypt.equals(encode(message));
    }

}
