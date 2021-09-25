package com.hb.unic.common.security;

import lombok.extern.slf4j.Slf4j;
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
 * RSA非对称加密
 *
 * @author Mr.Huang
 * @version v0.1, 2020/5/27 16:41, create by huangbiao.
 */
@Slf4j
public class RSA {

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 填充
     */
    private static final String SIGN_TYPE = "SHA256WithRSA";

    /**
     * 加密
     * 
     * @param message
     *            原字符串
     * @param privateKey
     *            私钥
     * @return 加密结果
     */
    public static String encode(String message, String privateKey) {
        try {
            PrivateKey key = getPrivateKeyFromPKCS8(privateKey);
            Signature signature = Signature.getInstance(SIGN_TYPE);
            signature.initSign(key);
            signature.update(message.getBytes());
            byte[] signBytes = signature.sign();
            return new String(Base64Utils.encode(signBytes));
        } catch (Exception e) {
            log.error("RSA加密异常, message={}, privateKey={}", message, privateKey);
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
     * @param publicKey
     *            私钥
     * @return 校验结果
     */
    public static boolean verify(String message, String encrypt, String publicKey) {
        try {
            PublicKey key = getPublicKeyFromX509(publicKey);
            Signature signature = Signature.getInstance(SIGN_TYPE);
            signature.initVerify(key);
            signature.update(message.getBytes());
            return signature.verify(Base64Utils.decode(encrypt.getBytes()));
        } catch (Exception e) {
            log.error("RSA校验异常, message={}, publicKey={}, encrypt={}", message, publicKey, encrypt);
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成公/私钥
     *
     * @param algorithm
     *            加密类型
     * @return 公钥+私钥，数组第一个元素为公钥，第二个元素为私钥
     */
    private static String[] getKeys(String algorithm, int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] publicBytes = Base64Utils.encode(publicKey.getEncoded());
        byte[] privateBytes = Base64Utils.encode(privateKey.getEncoded());
        return new String[] {new String(publicBytes), new String(privateBytes)};
    }

    /**
     * 执行加密或者解密
     *
     * @param bytes
     *            待加密或带解密的byte数组
     * @param key
     *            密匙
     * @param algorithm
     *            加密类型
     * @param mode
     *            模式，是加密还是解密
     * @return byte[]
     */
    private static byte[] doFinal(byte[] bytes, Key key, String algorithm, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, key);
        return cipher.doFinal(bytes);
    }

    /**
     * 获取公钥对象
     *
     * @param publicKey
     *            公钥
     * @return PublicKey
     */
    private static PublicKey getPublicKeyFromX509(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA.ALGORITHM);
        byte[] publicBytes = Base64Utils.decode(publicKey.getBytes());
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    /**
     * 获取私钥对象
     *
     * @param privateKey
     *            私钥
     * @return PrivateKey
     */
    private static PrivateKey getPrivateKeyFromPKCS8(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA.ALGORITHM);
        byte[] privateBytes = Base64Utils.decode(privateKey.getBytes());
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
    }

}
