package com.hb.unic.crawler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * jsoup工具类
 *
 * @version v0.1, 2021/10/10 15:17, create by huangbiao.
 */
public class JsoupUtils {

    /**
     * 空文本
     */
    private static final String EMPTY_TEXT = "";

    /**
     * @ 符号，表示获取属性，后面跟属性值
     */
    private static final String ATTR_MARK = "@";

    /**
     * 根据url获取html
     * 
     * @param url
     *            url
     * @return 文档对象
     */
    public static Document get(String url) {
        try {
            return Jsoup.connect(url).timeout(10 * 1000).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找第一个满足条件的元素，并获取文本（支持在选择器后面加@xxx，表示获取元素的xxx属性）
     * 
     * @param element
     *            元素
     * @param cssQuery
     *            选择器
     * @return 结果
     */
    public static String getFirstContent(Element element, String cssQuery) {
        if (element == null || cssQuery == null) {
            return EMPTY_TEXT;
        }
        Element targetElement = null;
        String attrName = null;
        if (cssQuery.contains(ATTR_MARK)) {
            String standardCssQuery = cssQuery.substring(0, cssQuery.indexOf(ATTR_MARK));
            targetElement = element.selectFirst(standardCssQuery);
            attrName = cssQuery.substring(cssQuery.indexOf(ATTR_MARK) + 1);
        } else {
            targetElement = element.selectFirst(cssQuery);
        }
        if (targetElement == null) {
            return EMPTY_TEXT;
        }
        if (attrName != null) {
            return targetElement.attr(attrName);
        }
        return targetElement.text();
    }

    /**
     * 查找第一个包含某些文本的元素的内容
     *
     * @param element
     *            元素
     * @param cssQuery
     *            选择器
     * @param textParts
     *            子文本列表
     * @return 结果
     */
    public static String getFirstContentMatchingTexts(Element element, String cssQuery, List<String> textParts) {
        if (element == null || cssQuery == null || textParts == null) {
            return EMPTY_TEXT;
        }
        Elements elements = element.select(cssQuery);
        for (Element e : elements) {
            boolean allMatch = textParts.stream().allMatch(e.text()::contains);
            if (allMatch) {
                return e.text();
            }
        }
        return EMPTY_TEXT;
    }

    /**
     * 查找包含某段文本的元素的内容
     *
     * @param element
     *            元素
     * @param cssQuery
     *            选择器
     * @param textParts
     *            子文本列表
     * @return 结果
     */
    public static List<String> getContentsMatchingTexts(Element element, String cssQuery, List<String> textParts) {
        List<Element> elementList = getElementsMatchingTexts(element, cssQuery, textParts);
        return elementList.stream().map(Element::text).collect(Collectors.toList());
    }

    /**
     * 查找包含某段文本的元素列表
     *
     * @param element
     *            元素
     * @param cssQuery
     *            选择器
     * @param textParts
     *            子文本列表
     * @return 结果
     */
    public static List<Element> getElementsMatchingTexts(Element element, String cssQuery, List<String> textParts) {
        List<Element> list = new ArrayList<>();
        if (element == null || cssQuery == null || CollectionUtils.isEmpty(textParts)) {
            return list;
        }
        Elements elements = element.select(cssQuery);
        for (Element e : elements) {
            boolean allMatch = textParts.stream().allMatch(e.text()::contains);
            if (allMatch) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * 把table表格转换为集合
     * 
     * @param element
     *            表格元素
     * @return 集合
     */
    public static List<Map<String, String>> convertTable2List(Element element) {
        List<Map<String, String>> list = new ArrayList<>();
        if (element == null) {
            return list;
        }
        Elements headers = element.select(" table > thead > tr > th");
        if (headers.isEmpty()) {
            return list;
        }
        Elements trs = element.select(" table > tbody > tr");
        if (trs.isEmpty()) {
            return list;
        }
        Map<String, String> map = null;
        for (Element tr : trs) {
            map = new HashMap<>();
            Elements tds = tr.select("th,td");
            for (int i = 0; i < tds.size(); i++) {
                map.put(headers.get(i).text(), tds.get(i).text());
            }
            list.add(map);
        }
        return list;
    }

}
