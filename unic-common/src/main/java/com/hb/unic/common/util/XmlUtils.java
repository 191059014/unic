package com.hb.unic.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * ========== xml工具类 ==========
 *
 * @author Mr.huang
 * @date 2019年09月17日 10时41分
 */
public class XmlUtils {

    /**
     * the constant log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * java对象转换为xml文件
     *
     * @param obj
     *            对象
     * @param load
     *            java对象.Class
     * @return xml文件的String
     */
    public static String beanToXml(Object obj, Class<?> load) {
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * java对象转换为xml文件
     *
     * @param obj
     *            对象
     * @param load
     *            java对象.Class
     * @return xml文件的String
     */
    public static String beanToXmlIgnoreXmlHead(Object obj, Class<?> load) {
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(load);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将XML转为指定的POJO
     *
     * @param clazz
     *            xml对应的javabean
     * @param xmlStr
     *            xml字符串
     * @return javabean
     */
    public static <T> T xml2Bean(Class<T> clazz, String xmlStr) {
        try (Reader reader = new StringReader(xmlStr)) {
            // 实例化类
            JAXBContext context = JAXBContext.newInstance(clazz);
            // XML 转为对象的接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // 以文件流的方式传入这个string
            Object xmlObject = unmarshaller.unmarshal(reader);
            return (T)xmlObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
