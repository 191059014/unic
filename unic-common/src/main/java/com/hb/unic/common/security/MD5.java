package com.hb.unic.common.security;

import com.hb.unic.common.util.HexByteArrUtils;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * 单向加密，MD5，SHA
 *
 * @author Mr.Huang
 * @version v0.1, 2020/5/27 15:33, create by huangbiao.
 */
@Slf4j
public class MD5 {

    /**
     * 加密
     * 
     * @param message
     *            原字符串
     * @return md5结果
     */
    public static String encode(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return HexByteArrUtils.byteArr2HexStr(md.digest(message.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("{}MD5加密异常, message={}", message);
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
     * @return 校验结果
     */
    public static boolean verify(String message, String encrypt) {
        return encrypt != null && encrypt.equals(encode(message));
    }

}
