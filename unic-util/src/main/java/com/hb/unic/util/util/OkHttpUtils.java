package com.hb.unic.util.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
     * json编码
     */
    public static final String JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";

    /**
     * xml编码
     */
    public static final String XML_CHARSET_UTF_8 = "text/xml;charset=utf-8";

    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * ########## post请求 ##########
     *
     * @param url            请求的url
     * @param object         参数体
     * @param headers        请求头
     * @param timeOutSeconds 连接/读取超时秒数，connectTimeout，readTimeout
     * @return response的body信息
     */
    public static String post(String url, Object object, Map<String, String> headers, Long... timeOutSeconds) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        OkHttpClient okHttpClient = getOkHttpClient(timeOutSeconds);
        RequestBody requestBody = null;
        if (object instanceof JSONObject) {
            requestBody = FormBody.create(MediaType.get(JSON_CHARSET_UTF_8), ((JSONObject) object).toJSONString());
        } else if (object instanceof String) {
            requestBody = FormBody.create(MediaType.get(XML_CHARSET_UTF_8), ((String) object));
        } else if (object instanceof FormBody.Builder) {
            requestBody = ((FormBody.Builder) object).build();
        } else {
            throw new RuntimeException("cannot analysis the requestBody parameter");
        }
        Request.Builder builder = getRequestBuilder(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, headers.get(key));
            }
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
     * @param url     请求ur
     * @param headers 请求头
     * @return response的body信息
     */
    public static String get(String url, Map<String, String> headers, Long... timeOutSeconds) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        OkHttpClient okHttpClient = getOkHttpClient(timeOutSeconds);
        Request.Builder builder = getRequestBuilder(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, headers.get(key));
            }
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
        return new Request.Builder().url(url).addHeader("Content-Type", JSON_CHARSET_UTF_8);
    }

    /**
     * ########## 获取OkHttpClient对象 ##########
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient(Long... timeOutSeconds) {
        OkHttpClient.Builder builder = new OkHttpClient()
                .newBuilder();
        if (timeOutSeconds != null && timeOutSeconds.length > 0) {
            builder.connectTimeout(timeOutSeconds[0], TimeUnit.SECONDS);
            if (timeOutSeconds.length > 1) {
                builder.readTimeout(timeOutSeconds[1], TimeUnit.SECONDS);
            }
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
