package com.hb.unic.util.util;

import com.google.common.base.Stopwatch;
import com.hb.unic.util.constant.Consts;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ========== OkHttp工具 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.OkHttpUtils.java, v1.0
 * @date 2019年07月15日 21时00分
 */
public class OkHttpUtils {

    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * ########## post请求 ##########
     *
     * @param url     请求的url
     * @param reqBody 请求参数体
     * @return response的body信息
     */
    public static String postJson(String url, Object reqBody) throws Exception {
        return doPost(url, JsonUtils.toJson(reqBody), MediaType.get(Consts.JSON_CHARSET_UTF_8), null, null, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url     请求的url
     * @param reqBody 请求参数体
     * @param headers 请求头
     * @return response的body信息
     */
    public static String postJson(String url, Object reqBody, Map<String, String> headers) throws Exception {
        return doPost(url, JsonUtils.toJson(reqBody), MediaType.get(Consts.JSON_CHARSET_UTF_8), headers, null, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url            请求的url
     * @param reqBody        请求参数体
     * @param headers        请求头
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return response的body信息
     */
    public static String postJson(String url, Object reqBody, Map<String, String> headers, Long connectTimeout, Long readTimeout) throws Exception {
        return doPost(url, JsonUtils.toJson(reqBody), MediaType.get(Consts.JSON_CHARSET_UTF_8), headers, connectTimeout, readTimeout);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url 请求的url
     * @param xml 请求参数xml
     * @return response的body信息
     */
    public static String postXml(String url, String xml) throws Exception {
        return doPost(url, xml, MediaType.get(Consts.XML_CHARSET_UTF_8), null, null, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url     请求的url
     * @param xml     请求参数xml
     * @param headers 请求头
     * @return response的body信息
     */
    public static String postXml(String url, String xml, Map<String, String> headers) throws Exception {
        return doPost(url, xml, MediaType.get(Consts.XML_CHARSET_UTF_8), headers, null, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url            请求的url
     * @param xml            请求参数xml
     * @param headers        请求头
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return response的body信息
     */
    public static String postXml(String url, String xml, Map<String, String> headers, Long connectTimeout, Long readTimeout) throws Exception {
        return doPost(url, xml, MediaType.get(Consts.XML_CHARSET_UTF_8), headers, connectTimeout, readTimeout);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url            请求的url
     * @param reqBody        请求参数体
     * @param mediaType      请求参数媒体类型
     * @param headers        请求头
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return response的body信息
     */
    public static String doPost(String url, String reqBody, MediaType mediaType, Map<String, String> headers, Long connectTimeout, Long readTimeout) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        OkHttpClient okHttpClient = getOkHttpClient(connectTimeout, readTimeout);
        RequestBody requestBody = FormBody.create(mediaType, reqBody);
        Request.Builder builder = getRequestBuilder(url);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.post(requestBody).build();
        LOGGER.info("post请求 => {}\n请求头：{}\n连接超时时间：{}ms\n读取超时时间：{}ms", url, headers, okHttpClient.connectTimeoutMillis(), okHttpClient.readTimeoutMillis());
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String body = response.body() == null ? "" : response.body().string();
        LOGGER.info("post响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return body;
    }

    /**
     * ########## get请求 ##########
     *
     * @param url 请求ur
     * @return response的body信息
     */
    public static String doGet(String url) throws Exception {
        return doGet(url, null, null, null);
    }

    /**
     * ########## get请求 ##########
     *
     * @param url     请求ur
     * @param headers 请求头
     * @return response的body信息
     */
    public static String doGet(String url, Map<String, String> headers) throws Exception {
        return doGet(url, headers, null, null);
    }

    /**
     * ########## get请求 ##########
     *
     * @param url            请求ur
     * @param headers        请求头
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return response的body信息
     */
    public static String doGet(String url, Map<String, String> headers, Long connectTimeout, Long readTimeout) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        OkHttpClient okHttpClient = getOkHttpClient(connectTimeout, readTimeout);
        Request.Builder builder = getRequestBuilder(url);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.get().build();
        LOGGER.info("get请求 => {}\n请求头：{}\n连接超时时间：{}ms\n读取超时时间：{}ms", url, headers, okHttpClient.connectTimeoutMillis(), okHttpClient.readTimeoutMillis());
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String body = response.body() == null ? "" : response.body().string();
        LOGGER.info("get响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return body;

    }

    /**
     * ########## 获取Request.Builder对象 ##########
     *
     * @param url 请求url
     * @return Request.Builder对象
     */
    private static Request.Builder getRequestBuilder(String url) {
        return new Request.Builder().url(url).addHeader("Content-Type", Consts.JSON_CHARSET_UTF_8);
    }

    /**
     * ########## 获取OkHttpClient对象 ##########
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient(Long connectTimeout, Long readTimeout) {
        OkHttpClient.Builder builder = new OkHttpClient()
                .newBuilder();
        if (connectTimeout != null) {
            builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        }
        if (readTimeout != null) {
            builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        }
        return builder
                .sslSocketFactory(createSSLSocketFactory(), getTrustManager())
                .hostnameVerifier(getHostnameVerifier())
                .build();
    }


    /**
     * ########## okHttps去除ssl校验 ##########
     *
     * @return SSLSocketFactory
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{getTrustManager()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            LOGGER.error("createSSLSocketFactory exception: {}", e);
        }

        return ssfFactory;
    }

    /**
     * ########## 去除验证 ##########
     *
     * @return HostnameVerifier
     */
    private static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }

    /**
     * ########## 重写证书类 ##########
     *
     * @return X509TrustManager
     */
    private static X509TrustManager getTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

}
