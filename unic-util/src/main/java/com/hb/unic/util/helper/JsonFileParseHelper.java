package com.hb.unic.util.helper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ========== json文件解析工具 ==========
 *
 * @author Mr.huang
 * @version v1.0
 * @date 2019年06月15日 13时45分
 */
public class JsonFileParseHelper {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileParseHelper.class);

    public static void main(String[] args) {
        String jsonString = readJsonFile2String("static/app/test.json");
        LOGGER.info(jsonString);
    }

    /**
     * ########## 读取Json文件到字符串 ##########
     *
     * @param path 文件路径
     * @return 字符串
     */
    public static String readJsonFile2String(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        File jsonFile = null;
        String jsonString = StringUtils.EMPTY;
        try {
            jsonFile = classPathResource.getFile();
            jsonString = FileUtils.readFileToString(jsonFile, "utf-8");
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("read json file to string exception: {}", e);
            }
        }
        return jsonString;
    }

    /**
     * 以流的方式获取json文件内容
     *
     * @param path json文件路径
     * @return json
     */
    public static String readJsonFile2StringByStream(String path) {
        Resource resource = new ClassPathResource(path);
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            is = resource.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            sb.append(bos.toString("utf-8"));
        } catch (IOException e) {
            System.out.println(String.format("根据路径获取静态资源，异常：%s", e));
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
