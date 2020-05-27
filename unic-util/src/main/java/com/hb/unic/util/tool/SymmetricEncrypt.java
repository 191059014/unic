package com.hb.unic.util.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 对称加密，DES，AES
 *
 * @author Mr.Huang
 * @version v0.1, SymmetricEncrypt.java, 2020/5/27 16:40, create by huangbiao.
 */
public enum SymmetricEncrypt {

    DES("des", "") {
        @Override
        public String encode(String message, String secretKey) {
            String result = null;
            try {
                SecretKey sk = getSecretKey(secretKey);
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, sk);// 加密模式
                byte[] doFinalBytes = cipher.doFinal(message.getBytes());
                result = new String(doFinalBytes);
            } catch (Exception e) {
                LOGGER.error("加密失败：{}", e);
            }
            return result;
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            String result = null;
            try {
                SecretKey sk = getSecretKey(secretKey);
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, sk);// 解密模式
                byte[] doFinalBytes = cipher.doFinal(encrypt.getBytes());
                result = new String(doFinalBytes);
            } catch (Exception e) {
                LOGGER.error("解密失败：{}", e);
            }
            return result;
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return false;
        }
    },
    AES("aes", "") {
        @Override
        public String encode(String message, String secretKey) {
            return null;
        }

        @Override
        public String decode(String encrypt, String secretKey) {
            return null;
        }

        @Override
        public boolean verify(String message, String secretKey, String encrypt) {
            return false;
        }
    };

    /**
     * 加密的类型
     */
    private String algorithm;

    /**
     * 描述
     */
    private String desc;

    SymmetricEncrypt(String algorithm, String desc) {
        this.algorithm = algorithm;
        this.desc = desc;
    }

    protected String getAlgorithm() {
        return algorithm;
    }

    protected String getDesc() {
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
     * 通过secretKey字符串获取SecretKey对象
     *
     * @param secretKey 密匙
     * @return SecretKey
     */
    protected SecretKey getSecretKey(String secretKey) throws Exception {
        byte[] keyBytes = secretKey.getBytes();
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(getAlgorithm());
        return factory.generateSecret(desKeySpec);
    }

}

    