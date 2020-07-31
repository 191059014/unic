package com.hb.unic.util;

import com.hb.unic.util.constant.Consts;
import com.hb.unic.util.tool.Base64Encrypt;
import org.junit.Test;

/**
 * base64测试
 *
 * @author Mr.Huang
 * @version v0.1, Base64EncryptTest.java, 2020/5/29 9:41, create by huangbiao.
 */
public class Base64EncryptTest {

    private String message = "123456您好==++@&";

    @Test
    public void testEncode() {
        System.out.println(Base64Encrypt.JDK.encode(message, Consts.UTF_8));
        System.out.println(Base64Encrypt.COMMONS_CODEC.encode(message, Consts.UTF_8));
    }

    @Test
    public void testDecode() {
        System.out.println(Base64Encrypt.JDK.decode("MTIzNDU25oKo5aW9PT0rK0Am", Consts.UTF_8));
        System.out.println(Base64Encrypt.COMMONS_CODEC.decode("MTIzNDU25oKo5aW9PT0rK0Am", Consts.UTF_8));
    }

    @Test
    public void testVerify() {
        System.out.println(Base64Encrypt.JDK.verify(message, "MTIzNDU25oKo5aW9PT0rK0Am", Consts.UTF_8));
        System.out.println(Base64Encrypt.COMMONS_CODEC.verify(message, "MTIzNDU25oKo5aW9PT0rK0Am", Consts.UTF_8));
    }

}

    