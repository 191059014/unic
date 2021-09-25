package com.hb.unic.common.util;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * ========== 压缩工具类 ==========
 *
 * @author Mr.huang
 * @version v1.0
 * @date 2019年07月16日 11时42分
 */
public class CompressUtils {

    /**
     * 压缩
     *
     * @param data 待压缩的字节码
     * @return 压缩后的字节码数组
     */
    public static byte[] compress(byte[] data) {
        GZIPOutputStream gzip = null;
        ByteArrayOutputStream outData = null;
        try {
            outData = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(outData);
            gzip.write(data);
            gzip.close();
            return outData.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(gzip);
            IOUtils.closeQuietly(outData);
        }
        return null;
    }

    /**
     * 解压
     *
     * @param data 待解压的字节码
     * @return 解压后的字节码
     */
    public static byte[] uncompress(byte[] data) {
        InputStream in = null;
        GZIPInputStream gzip = null;
        try {
            in = new ByteArrayInputStream(data);
            gzip = new GZIPInputStream(in);
            byte[] bs = IOUtils.toByteArray(gzip);
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(gzip);
        }
        return null;
    }
}
