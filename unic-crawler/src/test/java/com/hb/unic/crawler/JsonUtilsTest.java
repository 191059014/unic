package com.hb.unic.crawler;

import com.hb.unic.crawler.util.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 工具类测试
 *
 * @version v0.1, 2021/10/16 19:13, create by huangbiao.
 */
public class JsonUtilsTest {

    /**
     * 静态网页
     */
    @Test
    public void testOracle() {
        Document document = JsoupUtils.get("https://www.oracle.com/security-alerts/");
        Elements elements = document.select("table.otable-tech-basic.otable-w2");
        for (Element element : elements) {
            System.out.println(element);
        }
        List<Map<String, String>> list = JsoupUtils.convertTable2List(elements.get(0));
        System.out.println(list);
        System.out.println(JsoupUtils.selectFirstText(elements.get(0), "table@class"));
        System.out.println(JsoupUtils.selectFirstText(elements.get(0), "td", Arrays.asList("September")));
        System.out.println(JsoupUtils.selectText(elements.get(0), "td", Arrays.asList("February")));
        System.out.println(JsoupUtils.select(elements.get(0), "td", Arrays.asList("February")));
    }

    /**
     * 动态网页，TODO
     */
    @Test
    public void testToutiao() {
        Document document = JsoupUtils.get("https://www.toutiao.com/");
        Elements elements = document.select("div.right-container > div.hot-list > div > ul li a");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }

}
