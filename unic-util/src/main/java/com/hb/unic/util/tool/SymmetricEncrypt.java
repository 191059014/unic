package com.hb.unic.util.tool;

import com.hb.unic.util.util.HexByteArrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

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
            try {
                return HexByteArrUtils.byteArr2HexStr(jdkDes(message.getBytes(), secretKey, getAlgorithm(), getFillStyle(), Cipher.ENCRYPT_MODE));
            } catch (Exception e) {
                LOGGER.error("{}加密异常：{}，message：{}，secretKey：{}", getAlgorithm(), e, message, secretKey);
                return null;
            }
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            try {
                return new String(jdkDes(HexByteArrUtils.hexStr2byteArr(encrypt), secretKey, getAlgorithm(), getFillStyle(), Cipher.DECRYPT_MODE));
            } catch (Exception e) {
                LOGGER.error("{}解密异常：{}，encrypt：{}，secretKey：{}", getAlgorithm(), e, encrypt, secretKey);
                return null;
            }
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return encrypt != null && encrypt.equals(encode(message, secretKey));
        }
    },
    AES("AES", "AES/ECB/PKCS5Padding", "AES对称加密") {
        @Override
        public String encode(String message, String secretKey) {
            try {
                return HexByteArrUtils.byteArr2HexStr(jdkAes(message.getBytes(), secretKey, getAlgorithm(), getFillStyle(), Cipher.ENCRYPT_MODE));
            } catch (Exception e) {
                LOGGER.error("{}加密异常：{}，message：{}，secretKey：{}", getAlgorithm(), e, message, secretKey);
                return null;
            }
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            try {
                return new String(jdkAes(HexByteArrUtils.hexStr2byteArr(encrypt), secretKey, getAlgorithm(), getFillStyle(), Cipher.DECRYPT_MODE));
            } catch (Exception e) {
                LOGGER.error("{}解密异常：{}，encrypt：{}，secretKey：{}", getAlgorithm(), e, encrypt, secretKey);
                return null;
            }
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return encrypt != null && encrypt.equals(encode(message, secretKey));
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
     * jdk的AES加/解密
     *
     * @param bytes     待加密或带解密的byte数组
     * @param secretKey 密匙
     * @param algorithm 加密的类型
     * @param fillStyle 填充方式
     * @param mode      加/解密模式
     * @return 加/解密后的字符串
     */
    protected byte[] jdkAes(byte[] bytes, String secretKey, String algorithm, String fillStyle, int mode) throws Exception {
        SecretKey key = getSecretKey(algorithm, secretKey, 128);
        return doFinal(bytes, key, fillStyle, mode);
    }

    /**
     * jdk的DES加/解密
     *
     * @param bytes     待加密或带解密的byte数组
     * @param secretKey 密匙
     * @param fillStyle 填充方式
     * @param mode      模式，是加密还是解密
     * @return byte[]
     */
    protected byte[] jdkDes(byte[] bytes, String secretKey, String algorithm, String fillStyle, int mode) throws Exception {
        SecretKey desSecretKey = getDesSecretKey(algorithm, secretKey, 56);
        return doFinal(bytes, desSecretKey, fillStyle, mode);
    }

    /**
     * 执行加密或者解密
     *
     * @param bytes     待加密或带解密的byte数组
     * @param secretKey 密匙
     * @param fillStyle 填充方式
     * @param mode      模式，是加密还是解密
     * @return byte[]
     */
    public static byte[] doFinal(byte[] bytes, SecretKey secretKey, String fillStyle, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(fillStyle);
        cipher.init(mode, secretKey);
        return cipher.doFinal(bytes);
    }

    /**
     * 获取DES的SecretKey对象
     *
     * @param algorithm 加密的类型
     * @param secretKey 密匙
     * @param length    key长度
     * @return SecretKey
     */
    public static SecretKey getDesSecretKey(String algorithm, String secretKey, int length) throws Exception {
        SecretKey key = getSecretKey(algorithm, secretKey, length);
        DESKeySpec desKeySpec = new DESKeySpec(key.getEncoded());
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
        return factory.generateSecret(desKeySpec);
    }

    /**
     * 根据secretKey字符串获取SecretKey对象
     *
     * @param algorithm 加密的类型
     * @param secretKey 密匙
     * @param length    key长度
     * @return SecretKey
     */
    public static SecretKey getSecretKey(String algorithm, String secretKey, int length) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKey.getBytes());
        keyGenerator.init(length, secureRandom);
        return keyGenerator.generateKey();

    }

    /**
     * 生成密匙
     *
     * @return 密匙字符串
     */
    public static String generateKey(String algorithm, int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();
            return HexByteArrUtils.byteArr2HexStr(secretKey.getEncoded());
        } catch (Exception e) {
            LOGGER.error("{}生成密匙异常：{}", algorithm, e);
            return null;
        }
    }

}

    