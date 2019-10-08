package com.hb.unic.util.helper;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ========== json文件解析工具 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.helper.JsonFileParseHelper.java, v1.0
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
                LOGGER.error("read json file to string exception: {}", LogHelper.getStackTrace(e));
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
        try {
            File filePath = resource.getFile();
            String input = FileUtils.readFileToString(filePath, "UTF-8");
            return input;
        } catch (IOException e) {
            System.out.println(String.format("根据路径获取静态资源，异常：%s", e));
        }
        return null;
    }

}
