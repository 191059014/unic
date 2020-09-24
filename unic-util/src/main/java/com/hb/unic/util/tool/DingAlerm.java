package com.hb.unic.util.tool;

import com.hb.unic.util.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉告警
 *
 * @version v0.1, 2020/8/28 17:15, create by huangbiao.
 */
public class DingAlerm {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DingAlerm.class);

    /**
     * httpclient
     */
    private static HttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送普通文本消息
     *
     * @param webhook   机器人地址
     * @param text      消息文本
     * @param isAtAll   是否@所有人
     * @param atMobiles 需要@的人的手机号
     * @return true为发送成功，false为发送失败
     */
    public static boolean sendTextMessage(String webhook, String text, boolean isAtAll, List<String> atMobiles) {
        String baseLog = "[钉钉告警-DingAlerm-sendTextMessage]";
        try {
            HttpPost httppost = new HttpPost(webhook);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(buildTextMessage(text, isAtAll, atMobiles), "utf-8");
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            String result = EntityUtils.toString(response.getEntity());
            LOGGER.info("{}发送普通消息结果：{}", baseLog, result);
            return true;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("{}发送普通消息异常=", baseLog, e);
            }
            return false;
        }

    }

    /**
     * 组装text消息
     *
     * @param text      文本消息
     * @param isAtAll   是否@所有人
     * @param atMobiles 需要@人的手机号
     * @return json
     */
    private static String buildTextMessage(String text, boolean isAtAll, List<String> atMobiles) {
        Map<String, Object> items = new HashMap();
        items.put("msgtype", "text");
        Map<String, String> textContent = new HashMap();
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("text should not be blank");
        } else {
            textContent.put("content", text);
            items.put("text", textContent);
            Map<String, Object> atItems = new HashMap();
            if (isAtAll) {
                atItems.put("isAtAll", isAtAll);
            }
            if (atMobiles != null && !atMobiles.isEmpty()) {
                atItems.put("atMobiles", atMobiles);
            }
            items.put("at", atItems);
            return JsonUtils.toJson(items);
        }
    }

}

    