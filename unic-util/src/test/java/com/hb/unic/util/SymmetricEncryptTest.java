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

    @Test
    public void testGenerateKey() {
        String s = SymmetricEncrypt.generateKey(SymmetricEncrypt.AES.getAlgorithm());
        System.out.println(s);
    }

    @Test
    public void testDesEncode() {
        String s = SymmetricEncrypt.AES.encode("123","7c66db055f1413153f40e9693b34f1a4");
        System.out.println(s);
    }

}

    