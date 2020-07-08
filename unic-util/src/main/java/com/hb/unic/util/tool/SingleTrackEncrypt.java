package com.hb.unic.util.tool;

import com.hb.unic.util.util.HexByteArrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 单向加密，MD5，SHA
 *
 * @author Mr.Huang
 * @version v0.1, SingleTrackEncrypt.java, 2020/5/27 15:33, create by huangbiao.
 */
public enum SingleTrackEncrypt {

    MD5("MD5", "MD5(Message Digest algorithm 5，信息摘要算法)") {
        @Override
        public String encode(String message, String charsetName) {
            return encode(getAlgorithm(), message, charsetName);
        }

        @Override
        public boolean verify(String message, String encrypt, String charsetName) {
            return encrypt != null && encrypt.equals(encode(message, charsetName));
        }
    },
    SHA_1("SHA-1", "SHA(Secure Hash Algorithm，安全散列算法）") {
        @Override
        public String encode(String message, String charsetName) {
            return encode(getAlgorithm(), message, charsetName);
        }

        @Override
        public boolean verify(String message, String encrypt, String charsetName) {
            return encrypt.equals(encode(message, charsetName));
        }
    },
    SHA_256("SHA-256", "SHA(Secure Hash Algorithm，安全散列算法）") {
        @Override
        public String encode(String message, String charsetName) {
            return encode(getAlgorithm(), message, charsetName);
        }

        @Override
        public boolean verify(String message, String encrypt, String charsetName) {
            return encrypt != null && encrypt.equals(encode(message, charsetName));
        }
    };

    /**
     * 加密的类型
     */
    public String algorithm;

    /**
     * 描述
     */
    public String desc;

    SingleTrackEncrypt(String algorithm, String desc) {
        this.algorithm = algorithm;
        this.desc = desc;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleTrackEncrypt.class);

    /**
     * 加密
     *
     * @param message     待加密的信息
     * @param charsetName 编码
     * @return 加密后的信息
     */
    public abstract String encode(String message, String charsetName);

    /**
     * 校验
     *
     * @param message     原信息
     * @param encrypt     加密的字符串
     * @param charsetName 编码
     * @return true为验证通过，false为不通过
     */
    public abstract boolean verify(String message, String encrypt, String charsetName);

    /**
     * 将摘要信息转换成相对应编码
     *
     * @param algorithm   加密类型
     * @param message     摘要信息
     * @param charsetName 编码
     * @return 对应的编码字符串
     */
    protected static String encode(String algorithm, String message, String charsetName) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return HexByteArrUtils.byteArr2HexStr(md.digest(message.getBytes(charsetName)));
        } catch (Exception e) {
            LOGGER.error("{}加密异常: ", algorithm, e);
            return null;
        }
    }

}
