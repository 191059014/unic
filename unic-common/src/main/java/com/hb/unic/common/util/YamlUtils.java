package com.hb.unic.common.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

/**
 * ========== yml文件工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.YamlUtils.java, v1.0
 * @date 2019年07月14日 11时55分
 */
public class YamlUtils {

    public static void main(String[] args) {
        Properties properties = readYml("common/general-config.yml");
        System.out.println(111);
    }

    /**
     * 读取yaml文件
     *
     * @param path yaml文件路径
     * @return yaml文件所有属性
     */
    public static Properties readYml(String path) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(path));
        return yaml.getObject();
    }

}
