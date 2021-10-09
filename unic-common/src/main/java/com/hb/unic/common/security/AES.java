package com.hb.unic.common.security;

import com.hb.unic.common.util.HexByteArrUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * AES对称加密
 *
 * @author Mr.Huang
 * @version v0.1, 2020/5/27 16:40, create by huangbiao.
 */
@Slf4j
public class AES {

    /**
     * 算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 填充
     */
    private static final String FILLSTYLE = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * 
     * @param message
     *            原字符串
     * @param secretKey
     *            秘钥
     * @return 加密后的结果
     */
    public static String encode(String message, String secretKey) {
        try {
            return HexByteArrUtils.byteArr2HexStr(jdkAes(message.getBytes(), secretKey, Cipher.ENCRYPT_MODE));
        } catch (Exception e) {
            log.error("{}AES加密异常, message={}, secretKey={}", message, secretKey);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     * 
     * @param message
     *            原字符串
     * @param secretKey
     *            秘钥
     * @return 解密后的结果
     */
    public static String decode(String message, String secretKey) {
        try {
            return new String(jdkAes(HexByteArrUtils.hexStr2byteArr(message), secretKey, Cipher.DECRYPT_MODE));
        } catch (Exception e) {
            log.error("AES解密异常, message={}, secretKey={}", message, secretKey);
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
     * @param secretKey
     *            秘钥
     * @return 校验结果
     */
    public static boolean verify(String message, String encrypt, String secretKey) {
        return encrypt != null && encrypt.equals(encode(message, secretKey));
    }

    /**
     * jdk的AES加/解密
     *
     * @param bytes
     *            待加密或带解密的byte数组
     * @param secretKey
     *            密匙
     * @param mode
     *            加/解密模式
     * @return 加/解密后的字符串
     */
    private static byte[] jdkAes(byte[] bytes, String secretKey, int mode) throws Exception {
        SecretKey key = getSecretKey(secretKey);
        return doFinal(bytes, key, mode);
    }

    /**
     * 执行加密或者解密
     *
     * @param bytes
     *            待加密或带解密的byte数组
     * @param secretKey
     *            密匙
     * @param mode
     *            模式，是加密还是解密
     * @return byte[]
     */
    private static byte[] doFinal(byte[] bytes, SecretKey secretKey, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(FILLSTYLE);
        cipher.init(mode, secretKey);
        return cipher.doFinal(bytes);
    }

    /**
     * 根据secretKey字符串获取SecretKey对象
     *
     * @param secretKey
     *            密匙
     * @return SecretKey
     */
    private static SecretKey getSecretKey(String secretKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKey.getBytes());
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey();
    }

}
