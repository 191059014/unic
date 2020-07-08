package com.hb.unic.util;

import com.hb.unic.util.constant.UtilConsts;
import com.hb.unic.util.tool.SingleTrackEncrypt;
import org.junit.Test;

/**
 * 单向加密单元测试
 *
 * @author Mr.Huang
 * @version v0.1, SingleTrackEncryptTest.java, 2020/5/27 15:54, create by huangbiao.
 */
public class SingleTrackEncryptTest {

    private String message = "123456您好==++@&";

    @Test
    public void testEncode() {
        String md5_encode = SingleTrackEncrypt.MD5.encode(message, UtilConsts.ISO_8859_1);
        System.out.println(md5_encode + "\t长度：" + md5_encode.length());
        String sha_1_encode = SingleTrackEncrypt.SHA_1.encode(message, UtilConsts.ISO_8859_1);
        System.out.println(sha_1_encode + "\t长度：" + sha_1_encode.length());
        String sha_256_encode = SingleTrackEncrypt.SHA_256.encode(message, UtilConsts.ISO_8859_1);
        System.out.println(sha_256_encode + "\t长度：" + sha_256_encode.length());
    }

    @Test
    public void testVerify() {
        System.out.println(SingleTrackEncrypt.MD5.verify(message, "e3eccac1919ca38f864037c8d171967c", UtilConsts.ISO_8859_1));
        System.out.println(SingleTrackEncrypt.SHA_1.verify(message, "c9a2ef34cf097ecd828bc57f0a3fc755448c2b5e", UtilConsts.ISO_8859_1));
        System.out.println(SingleTrackEncrypt.SHA_256.verify(message, "82f37f2da56f00fd70c667263b05ca52e661dce419d73d4a9ad5a1685e6a6be8", UtilConsts.ISO_8859_1));
    }

}

    