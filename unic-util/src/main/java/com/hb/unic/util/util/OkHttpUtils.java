package com.hb.unic.util.util;

import com.alibaba.fastjson.JSONObject;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import okhttp3.*;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

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
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * ########## post请求 ##########
     *
     * @param url     请求的url
     * @param object  参数体
     * @param headers 请求头
     * @return response的body信息
     */
    public static String post(String url, Object object, Map<String, String> headers) throws Exception {
        OkHttpClient okHttpClient = getOkHttpClient();
        RequestBody requestBody = null;
        if (object instanceof JSONObject) {
            requestBody = FormBody.create(MediaType.get(JSON_CHARSET_UTF_8), ((JSONObject) object).toJSONString());
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
        LOGGER.info("do post, url={}, headers={}, body={}", url, headers, object);
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body() == null ? "" : response.body().string();
    }

    /**
     * ########## get请求 ##########
     *
     * @param url     请求ur
     * @param headers 请求头
     * @return response的body信息
     */
    public static String get(String url, Map<String, String> headers) throws Exception {
        OkHttpClient okHttpClient = getOkHttpClient();
        Request.Builder builder = getRequestBuilder(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.get().build();
        LOGGER.info("do get, url={}, headers={}", url, headers);
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body() == null ? "" : response.body().string();

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
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
                .newBuilder().sslSocketFactory(createSSLSocketFactory(), getTrustManager())
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
            LOGGER.error("createSSLSocketFactory exception:{}", LogHelper.getStackTrace(e));
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