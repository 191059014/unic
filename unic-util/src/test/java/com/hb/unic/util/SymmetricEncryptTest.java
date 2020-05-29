package com.hb.unic.util;

import com.hb.unic.util.tool.SymmetricEncrypt;
import org.junit.Test;

/**
 * 对称加密测试
 *
 * @author Mr.Huang
 * @version v0.1, SymmetricEncryptTest.java, 2020/5/28 10:03, create by huangbiao.
 */
public class SymmetricEncryptTest {

    private String message = "123456您好==++@&";

    private String secretKey = "a1eaddf53b3a93a678a47830e180f444";

    @Test
    public void testGenerateKey() {
        String aes = SymmetricEncrypt.generateKey(SymmetricEncrypt.AES.getAlgorithm(), 128);
        System.out.println(aes + "\t长度：" + aes.length());
        String des = SymmetricEncrypt.generateKey(SymmetricEncrypt.DES.getAlgorithm(), 56);
        System.out.println(des + "\t长度：" + des.length());
    }

    @Test
    public void testEncode() {
        System.out.println(SymmetricEncrypt.AES.encode(message, secretKey));
        System.out.println(SymmetricEncrypt.DES.encode(message, secretKey));
    }

    @Test
    public void testDecode() {
        System.out.println(SymmetricEncrypt.AES.decode("65b1ab05b6bad504e709883bb9750f6a3fc77f13b6b87135ef372996bd4a33ab", secretKey));
        System.out.println(SymmetricEncrypt.DES.decode("a513d1a1037f813d265b8994d94b13869de9d86e15c21b5c", secretKey));
    }

    @Test
    public void testVerify() {
        System.out.println(SymmetricEncrypt.AES.verify(message, secretKey, "65b1ab05b6bad504e709883bb9750f6a3fc77f13b6b87135ef372996bd4a33ab"));
        System.out.println(SymmetricEncrypt.DES.verify(message, secretKey, "a513d1a1037f813d265b8994d94b13869de9d86e15c21b5c"));
    }

}

    