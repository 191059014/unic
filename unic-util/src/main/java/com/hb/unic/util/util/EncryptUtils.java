package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.tool.LogHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ########## 基于MessageDigest实现常用加密工具类 ##########
 */
public class EncryptUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);

    private static final String DEFAULT_ENCODE_CODE = "MD5";

    public static void main(String[] args) {
        String code = "123";
        LOGGER.info(encode(code));
    }

    /**
     * 将摘要信息转换成MD5编码串
     *
     * @param message 摘要信息
     * @return 对应的编码字符串
     */
    public static String encode(String message) {

        return encode(DEFAULT_ENCODE_CODE, message);
    }

    /**
     * 将摘要信息转换成相对应编码
     *
     * @param code    编码类型
     * @param message 摘要信息
     * @return 对应的编码字符串
     */
    public static String encode(String code, String message) {
        String encode = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(code);
            encode = byteArr2HexStr(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("MessageDigest Encode error", LogHelper.getStackTrace(e));
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("byteArr2HexStr error", LogHelper.getStackTrace(e));
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
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
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
