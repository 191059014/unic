package com.hb.unic.crawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * jsoup工具类
 *
 * @version v0.1, 2021/10/10 15:17, create by huangbiao.
 */
public class JsoupUtils {

    public static void main(String[] args) throws Exception {
        Document document = get("https://kuaibao.jd.com/?ids=268177914,265291768,256778257,256787068");
        Elements elements = document
            .select("#list-646 > li:nth-child(1) > a > div._3A06q0i5gFYApDhhEM4i78 > div._27CzaCw-p0CS-ah_PEIDAm");
        for (Element element : elements) {
            System.out.println(element);
        }
    }

    /**
     * 根据url获取html
     * 
     * @param url
     *            url
     * @return 文档对象
     */
    public static Document get(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
