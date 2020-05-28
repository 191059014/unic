package com.hb.unic.util.tool;

import com.hb.unic.util.constant.UtilConstants;
import com.hb.unic.util.util.HexByteArrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密，DES，AES
 *
 * @author Mr.Huang
 * @version v0.1, SymmetricEncrypt.java, 2020/5/27 16:40, create by huangbiao.
 */
public enum SymmetricEncrypt {

    DES("DES", "DES/ECB/PKCS5Padding", "DES对称加密") {
        @Override
        public String encode(String message, String secretKey) {
            return jdkDesAES(message, secretKey, getAlgorithm(), getFillStyle(), Cipher.ENCRYPT_MODE);
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            return jdkDesAES(encrypt, secretKey, getAlgorithm(), getFillStyle(), Cipher.DECRYPT_MODE);
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return encrypt.equals(encode(message, secretKey));
        }
    },
    AES("AES", "AES/ECB/PKCS5Padding", "AES对称加密") {
        @Override
        public String encode(String message, String secretKey) {
            return jdkDesAES(message, secretKey, getAlgorithm(), getFillStyle(), Cipher.ENCRYPT_MODE);
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            return jdkDesAES(encrypt, secretKey, getAlgorithm(), getFillStyle(), Cipher.DECRYPT_MODE);
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return encrypt.equals(encode(message, secretKey));
        }
    };

    /**
     * 加密的类型
     */
    private String algorithm;

    /**
     * 填充方式
     */
    private String fillStyle;

    /**
     * 描述
     */
    private String desc;

    SymmetricEncrypt(String algorithm, String fillStyle, String desc) {
        this.algorithm = algorithm;
        this.fillStyle = fillStyle;
        this.desc = desc;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getFillStyle() {
        return fillStyle;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricEncrypt.class);

    /**
     * 加密
     *
     * @param message   待加密的信息
     * @param secretKey 密匙
     * @return 加密后的信息
     */
    public abstract String encode(String message, String secretKey);

    /**
     * 解密
     *
     * @param encrypt   加密的信息
     * @param secretKey 密匙
     * @return 解密后的信息
     */
    public abstract String decode(String encrypt, String secretKey);

    /**
     * 校验
     *
     * @param message   原信息
     * @param secretKey 密匙
     * @param encrypt   加密的字符串
     * @return true为验证通过，false为不通过
     */
    public abstract boolean verify(String message, String secretKey, String encrypt);

    /**
     * des/aes 加/解密
     *
     * @param source    待加/解密的字符串
     * @param secretKey 密匙
     * @param algorithm 加密的类型
     * @param fillStyle 填充方式
     * @param mode      加/解密模式
     * @return 加/解密后的字符串
     */
    protected String jdkDesAES(String source, String secretKey, String algorithm, String fillStyle, int mode) {
        try {
            /**
             * 生成SecretKey
             */
            byte[] keyBytes = secretKey.getBytes();

            KeyGenerator kgen = KeyGenerator.getInstance(algorithm);
            kgen.init(128);
            SecretKey key = kgen.generateKey();
//            SecretKey key = new SecretKeySpec(keyBytes, algorithm);
            /**
             * 加密 or 解密
             */
            Cipher cipher = Cipher.getInstance(fillStyle);
            cipher.init(mode, key);// 加/解密模式
            byte[] doFinalBytes = cipher.doFinal(source.getBytes(UtilConstants.CHARSET_UTF8));
            return new String(doFinalBytes, UtilConstants.CHARSET_UTF8);
        } catch (Exception e) {
            LOGGER.error("加/解密异常：{}，source：{}，secretKey：{}，algorithm：{}，fillStyle：{}，mode：{}", e, source, secretKey, algorithm, fillStyle, mode);
            return null;
        }

    }

    /**
     * 生成密匙
     *
     * @return 密匙字符串
     */
    public static String generateKey(String algorithm) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            // 初始化key的长度，只能是128
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return HexByteArrUtils.byteArr2HexStr(secretKey.getEncoded());
        } catch (Exception e) {
            LOGGER.error("生成密匙异常：{}", e);
            return null;
        }
    }

}

    