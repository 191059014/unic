package com.hb.unic.util.tool;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * ========== json文件解析工具 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.tool.JsonFileParseTools.java, v1.0
 * @date 2019年06月15日 13时45分
 */
public class JsonFileParseTools {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileParseTools.class);

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

}
