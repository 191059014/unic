package com.hb.unic.util;

import com.hb.unic.util.tool.SingleTrackEncrypt;
import org.junit.Test;

/**
 * 单向加密单元测试
 *
 * @author Mr.Huang
 * @version v0.1, SingleTrackEncryptTest.java, 2020/5/27 15:54, create by huangbiao.
 */
public class SingleTrackEncryptTest {

    @Test
    public void testMd5() {
        String encode = SingleTrackEncrypt.MD5.encode("123");
        System.out.println(encode);
    }

    @Test
    public void testSHA() {
        String encode = SingleTrackEncrypt.SHA.encode("123");
        System.out.println(encode);
    }

}

    