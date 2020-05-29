package com.hb.unic.util.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * base64加密算法
 *
 * @author Mr.Huang
 * @version v0.1, Base64Encrypt.java, 2020/5/29 9:29, create by huangbiao.
 */
public enum Base64Encrypt {

    /**
     * JDK实现Base64编码
     */
    JDK {
        @Override
        public String encode(String message) {
            return new String(Base64.getEncoder().encode(message.getBytes()));
        }

        @Override
        public String decode(String encrypt) {
            return new String(Base64.getDecoder().decode(encrypt.getBytes()));
        }

        @Override
        public boolean verify(String message, String encrypt) {
            return encrypt != null && encrypt.equals(encode(message));
        }
    },
    /**
     * Commons Codec实现base64编码
     */
    COMMONS_CODEC {
        @Override
        public String encode(String message) {
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(message.getBytes()));
        }

        @Override
        public String decode(String encrypt) {
            return new String(org.apache.commons.codec.binary.Base64.decodeBase64(encrypt.getBytes()));
        }

        @Override
        public boolean verify(String message, String encrypt) {
            return encrypt != null && encrypt.equals(encode(message));
        }
    };

    /**
     * 加密
     *
     * @param message 待加密的字符串
     * @return 加密后的字符串
     */
    public abstract String encode(String message);

    /**
     * 解密
     *
     * @param encrypt 加密的字符串
     * @return 解密后的字符串
     */
    public abstract String decode(String encrypt);

    /**
     * 校验
     *
     * @param message 待加密的字符串
     * @param encrypt 加密后的字符串
     * @return true为校验通过
     */
    public abstract boolean verify(String message, String encrypt);

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Encrypt.class);

}

    