package com.hb.unic.util.util;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * ========== Http工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.util.HttpUtils.java, v1.0
 * @date 2019年05月31日 10时12分
 */
public class HttpUtils {

    /**
     * The constant LOGGER.
     */
    protected static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 定义默认编码格式UTF-8
     */
    public static final String ENCODE_DEFAULT = "UTF-8";

    /**
     * The constant URL_PARAM_CONNECT_FLAG.
     */
    private static final String URL_PARAM_CONNECT_FLAG = "&";

    /**
     * The constant EMPTY.
     */
    private static final String EMPTY = "";

    /**
     * The constant connectionTimeOut.
     */
    private static int connectionTimeOut = 25000;

    /**
     * The constant socketTimeOut.
     */
    private static int socketTimeOut = 25000;

    /**
     * The constant maxConnectionPerHost.
     */
    private static int maxConnectionPerHost = 20;

    /**
     * The constant maxTotalConnections.
     */
    private static int maxTotalConnections = 20;

    /**
     * The constant connectionManager.
     */
    private static MultiThreadedHttpConnectionManager connectionManager = null;

    /**
     * The constant client.
     */
    private static HttpClient client;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST方式提交数据
     *
     * @param url      待请求的URL
     * @param params   要提交的数据
     * @param encoding 编码
     * @return 响应结果 string
     * @throws IOException IO异常
     */
    public static String post(String url, Map<String, String> params, String encoding) {
        String response = EMPTY;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(url);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
            // 将表单的值放入postMethod中
            if (params != null) {
                Set<String> keySet = params.keySet();
                for (String key : keySet) {
                    String value = params.get(key);
                    postMethod.addParameter(key, value);
                }
            }
            // 执行postMethod
            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("Response Status Code = " + postMethod.getStatusCode());
                }
            }
        } catch (HttpException e) {
            logger.error("HttpException", LogHelper.getStackTrace(e));
        } catch (IOException e) {
            logger.error("IOException", LogHelper.getStackTrace(e));
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }
        }

        return response;
    }

    /**
     * GET方式提交数据
     *
     * @param url      待请求的URL
     * @param params   要提交的数据
     * @param encoding 编码
     * @return 响应结果 string
     * @throws IOException IO异常
     */
    public static String get(String url, Map<String, String> params, String encoding) {

        String response = EMPTY;
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer(EMPTY);
        if (params != null) {
            if (strtTotalURL.indexOf("?") == -1) {
                strtTotalURL.append(url).append("?").append(getUrl(params, encoding));
            } else {
                strtTotalURL.append(url).append("&").append(getUrl(params, encoding));
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("GET请求URL = " + strtTotalURL.toString());
        }
        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
            // 执行getMethod
            int statusCode = client.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = getMethod.getResponseBodyAsString();
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("Response Status Code = " + getMethod.getStatusCode());
                }
            }
        } catch (HttpException e) {
            logger.error("HttpException", LogHelper.getStackTrace(e));
        } catch (IOException e) {
            logger.error("IOException", LogHelper.getStackTrace(e));
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }
        }

        return response;
    }

    /**
     * 据Map生成URL字符串
     *
     * @param map      Map
     * @param valueEnc URL编码
     * @return URL url
     */
    private static String getUrl(Map<String, String> map, String valueEnc) {
        if (null == map || map.keySet().size() == 0) {
            return (EMPTY);
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            String key = it.next();
            if (map.containsKey(key)) {
                String val = map.get(key);
                String str = val != null ? val : EMPTY;
                try {
                    str = URLEncoder.encode(str, valueEnc);
                    str = convertSymbol(str);
                } catch (UnsupportedEncodingException e) {
                    logger.error("unsupported encode: {}", LogHelper.getStackTrace(e));
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = EMPTY;
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return (strURL);
    }

    /**
     * ########## 特殊字符的处理 ##########
     *
     * @param str 参数值
     * @return 转换后的值
     */
    private static String convertSymbol(String str) {
        if (str.indexOf("%2C") > 0) {
            str = str.replaceAll("%2C", ",");
        }
        return str;
    }

}