package com.hb.unic.common.util;

import org.apache.commons.io.FileUtils;
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
public class JsonFileParseUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileParseUtils.class);

    public static void main(String[] args) {
        String jsonString = readJsonFile2String("static/app/test.json");
        LOGGER.info(jsonString);
    }

    /**
     * ########## 读取Json文件到字符串 ##########
     *
     * @param path
     *            文件路径
     * @return 字符串
     */
    public static String readJsonFile2String(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            File jsonFile = classPathResource.getFile();
            String jsonString = FileUtils.readFileToString(jsonFile, "utf-8");
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 以流的方式获取json文件内容
     *
     * @param path
     *            json文件路径
     * @return json
     */
    public static String readJsonFile2StringByStream(String path) {
        Resource resource = new ClassPathResource(path);
        StringBuilder sb = new StringBuilder();
        try (InputStream is = resource.getInputStream(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            sb.append(bos.toString("utf-8"));
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
