package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * ========== groovy工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.GroovyUtils.java, v1.0
 * @date 2019年06月16日 09时14分
 */
public class GroovyUtils {

    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(GroovyUtils.class);

    public static void main(String[] args) {
        Object value = evaluateMethod("groovy/", "StockRule.groovy", "test", "huangbiao");
        LOGGER.info("groovy test result:{}", value);
    }

    /**
     * ########## 执行整个groovy脚本文件 ##########
     *
     * @param map 参数
     * @return 执行结果
     */
    public static Object evaluateFile(Map<String, Object> map, String groovyFilePath) {
        Binding binding = new Binding();
        if (map != null) {
            for (String k : map.keySet()) {
                Object v = map.get(k);
                binding.setProperty(k, v);
            }
        }
        GroovyShell groovyShell = new GroovyShell(binding);
        URI uri = null;
        Object value = null;
        try {
            uri = Thread.currentThread().getContextClassLoader().getResource(groovyFilePath).toURI();
            value = groovyShell.evaluate(uri);
        } catch (URISyntaxException e) {
            LOGGER.error(LogHelper.getStackTrace(e));
        } catch (IOException e) {
            LOGGER.error(LogHelper.getStackTrace(e));
        }

        return value;
    }

    /**
     * ########## 执行groovy类中的某个方法 ##########
     *
     * @param rootPath   groovy类根路径
     * @param scriptName grrovy类名
     * @param methodName 需要执行的方法名称
     * @param params     需要执行的方法的参数
     * @return Object
     */
    public static Object evaluateMethod(String rootPath, String scriptName, String methodName, Object... params) {
        Object result = null;
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(rootPath);
            if (url == null) {
                LOGGER.warn("cannot get resource from path: ", rootPath);
                return null;
            }
            GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine(url.toURI().getPath());
            Class scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
            GroovyObject scriptInstance = (GroovyObject) scriptClass.newInstance();
            result = scriptInstance.invokeMethod(methodName, params);
        } catch (Exception e) {
            LOGGER.error("evaluate groovy method exception: " + LogHelper.getStackTrace(e));
        }
        return result;
    }

}
