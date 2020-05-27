package com.hb.unic.util.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单向加密，MD5，SHA
 *
 * @author Mr.Huang
 * @version v0.1, SingleTrackEncrypt.java, 2020/5/27 15:33, create by huangbiao.
 */
public enum SingleTrackEncrypt {

    MD5("MD5", "MD5(Message Digest algorithm 5，信息摘要算法)") {
        @Override
        public String encode(String message) {
            return encode(getAlgorithm(), message);
        }

        @Override
        public boolean verify(String message, String encrypt) {
            return encrypt.equals(encode(message));
        }
    },
    SHA("SHA", "SHA(Secure Hash Algorithm，安全散列算法）") {
        @Override
        public String encode(String message) {
            return encode(getAlgorithm(), message);
        }

        @Override
        public boolean verify(String message, String encrypt) {
            return encrypt.equals(encode(message));
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

    SingleTrackEncrypt(String algorithm, String desc) {
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleTrackEncrypt.class);

    /**
     * 加密
     *
     * @param message 待加密的信息
     * @return 加密后的信息
     */
    public abstract String encode(String message);

    /**
     * 校验
     *
     * @param message 原信息
     * @param encrypt 加密的字符串
     * @return true为验证通过，false为不通过
     */
    public abstract boolean verify(String message, String encrypt);

    /**
     * 将摘要信息转换成相对应编码
     *
     * @param algorithm 加密类型
     * @param message   摘要信息
     * @return 对应的编码字符串
     */
    protected static String encode(String algorithm, String message) {
        String encode = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
            encode = byteArr2HexStr(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("MessageDigest Encode error: {}", e);
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("byteArr2HexStr error: {}", e);
            }
        }
        return encode;
    }

    /**
     * 将byte数组转换为表示16进制值的字符串
     *
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    protected static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

}
