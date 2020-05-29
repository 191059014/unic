package com.hb.unic.util.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 *
 * @author Mr.Huang
 * @version v0.1, UnSymmetricEncrypt.java, 2020/5/27 16:41, create by huangbiao.
 */
public enum UnSymmetricEncrypt {

    SHA256WithRSA("RSA", "SHA256WithRSA", "RSA2签名算法") {
        @Override
        public String encode(String message, String privateKey) {
            try {
                PrivateKey key = getPrivateKeyFromPKCS8(getAlgorithm(), privateKey);
                Signature signature = Signature.getInstance(getSignType());
                signature.initSign(key);
                signature.update(message.getBytes());
                byte[] signBytes = signature.sign();
                return new String(Base64Utils.encode(signBytes));
            } catch (Exception e) {
                LOGGER.error("{}加密异常：{}，message：{}，privateKey：{}", getSignType(), e, message, privateKey);
                return null;
            }
        }

        @Override
        public boolean verify(String message, String publicKey, String encrypt) {
            try {
                PublicKey key = getPublicKeyFromX509(getAlgorithm(), publicKey);
                Signature signature = Signature.getInstance(getSignType());
                signature.initVerify(key);
                signature.update(message.getBytes());
                return signature.verify(Base64Utils.decode(encrypt.getBytes()));
            } catch (Exception e) {
                LOGGER.error("{}校验异常：{}，message：{}, encrypt：{}，publicKey：{}", getSignType(), e, message, encrypt, publicKey);
                return false;
            }
        }
    };

    /**
     * 加密的类型
     */
    private String algorithm;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 描述
     */
    private String desc;

    UnSymmetricEncrypt(String algorithm, String signType, String desc) {
        this.algorithm = algorithm;
        this.signType = signType;
        this.desc = desc;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getSignType() {
        return signType;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SymmetricEncrypt.class);

    /**
     * 私钥加密
     *
     * @param message    待加密的信息
     * @param privateKey 私钥
     * @return 加密后的信息
     */
    public abstract String encode(String message, String privateKey);

    /**
     * 校验
     *
     * @param message   原信息
     * @param publicKey 公钥
     * @param encrypt   加密的字符串
     * @return true为验证通过，false为不通过
     */
    public abstract boolean verify(String message, String publicKey, String encrypt);

    /**
     * 生成公/私钥
     *
     * @param algorithm 加密类型
     * @return 公钥+私钥，数组第一个元素为公钥，第二个元素为私钥
     */
    public static String[] getKeys(String algorithm, int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] publicBytes = Base64Utils.encode(publicKey.getEncoded());
        byte[] privateBytes = Base64Utils.encode(privateKey.getEncoded());
        return new String[]{new String(publicBytes), new String(privateBytes)};
    }

    /**
     * 执行加密或者解密
     *
     * @param bytes     待加密或带解密的byte数组
     * @param key       密匙
     * @param algorithm 加密类型
     * @param mode      模式，是加密还是解密
     * @return byte[]
     */
    public static byte[] doFinal(byte[] bytes, Key key, String algorithm, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, key);
        return cipher.doFinal(bytes);
    }

    /**
     * 获取公钥对象
     *
     * @param algorithm 加密类型
     * @param publicKey 公钥
     * @return PublicKey
     */
    public static PublicKey getPublicKeyFromX509(String algorithm, String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] publicBytes = Base64Utils.decode(publicKey.getBytes());
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    /**
     * 获取私钥对象
     *
     * @param algorithm  加密类型
     * @param privateKey 私钥
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] privateBytes = Base64Utils.decode(privateKey.getBytes());
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
    }

}

    