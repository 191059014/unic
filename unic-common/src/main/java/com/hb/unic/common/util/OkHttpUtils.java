package com.hb.unic.common.util;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ========== OkHttp工具 ==========
 *
 * @author Mr.huang
 * @version v1.0
 * @date 2019年07月15日 21时00分
 */
public class OkHttpUtils {

    /**
     * form类型
     */
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * json编码
     */
    private static final String JSON = "application/json;charset=utf-8";

    /**
     * xml编码
     */
    private static final String XML = "text/xml;charset=utf-8";

    /**
     * Content-Type
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * The constant LOGGER.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * 默认okhttp单例对象
     */
    private static final OkHttpClient DEFAULT_CLIENT = getOkHttpClient(15L, 15L, 15L);

    /**
     * ########## get请求 ##########
     *
     * @param url
     *            请求ur
     * @return response的body信息
     */
    public static String get(String url) {
        return doGet(url, null);
    }

    /**
     * ########## get请求 ##########
     *
     * @param url
     *            请求ur
     * @param headers
     *            请求头 读取超时时间
     * @return response的body信息
     */
    public static String doGet(String url, Map<String, String> headers) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.get().build();
        LOGGER.info("get请求 => {}\n请求头：{}", url, headers);
        Response response = null;
        String body = null;
        try {
            response = DEFAULT_CLIENT.newCall(request).execute();
            body = response.body() == null ? "" : response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        LOGGER.info("get响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopWatch.getTotalTimeMillis());
        return body;

    }

    /**
     * ########## post请求 ##########
     *
     * @param url
     *            请求的url
     * @param reqBody
     *            请求参数体
     * @return response的body信息
     */
    public static String postJson(String url, Object reqBody) {
        return postJson(url, reqBody, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url
     *            请求的url
     * @param reqBody
     *            请求参数体
     * @param headers
     *            请求头
     * @return response的body信息
     */
    public static String postJson(String url, Object reqBody, Map<String, String> headers) {
        return doPost(url, JsonUtils.toJson(reqBody), MediaType.get(JSON), headers);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url
     *            请求的url
     * @param xml
     *            请求参数xml
     * @return response的body信息
     */
    public static String postXml(String url, String xml) {
        return postXml(url, xml, null);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url
     *            请求的url
     * @param xml
     *            请求参数xml
     * @param headers
     *            请求头
     * @return response的body信息
     */
    public static String postXml(String url, String xml, Map<String, String> headers) {
        return doPost(url, xml, MediaType.get(XML), headers);
    }

    /**
     * ########## post请求 ##########
     *
     * @param url
     *            请求的url
     * @param reqBody
     *            请求参数体
     * @param mediaType
     *            请求参数媒体类型
     * @param headers
     *            请求头
     * @return response的body信息
     */
    public static String doPost(String url, String reqBody, MediaType mediaType, Map<String, String> headers) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        RequestBody requestBody = FormBody.create(mediaType, reqBody);
        Request.Builder builder = new Request.Builder().url(url).addHeader(CONTENT_TYPE, JSON);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.post(requestBody).build();
        LOGGER.info("post请求 => {}\n请求头：{}\n请求体：{}", url, headers, reqBody);
        Response response = null;
        String body = null;
        try {
            response = DEFAULT_CLIENT.newCall(request).execute();
            body = response.body() == null ? "" : response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        LOGGER.info("post响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopWatch.getTotalTimeMillis());
        return body;
    }

    /**
     * form方式
     * 
     * @param url
     *            url
     * @param headers
     *            请求头
     * @param params
     *            form参数
     * @return 结果
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, String> params) {
        return postForm(url, headers, params, null, null, null);
    }

    /**
     * form方式，支持单个文件参数
     *
     * @param url
     *            url
     * @param headers
     *            请求头
     * @param params
     *            form参数
     * @param fileParamName
     *            文件参数的参数名
     * @param fileName
     *            文件名称
     * @param fileText
     *            文件内容
     * @return 结果
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, String> params,
        String fileParamName, String fileName, String fileText) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        /*
         * 设置文件类型的参数
         */
        if (fileParamName != null) {
            multipartBuilder.addFormDataPart(fileParamName, fileName,
                RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), fileText.getBytes()));
        }
        /*
         * 设置普通类型的参数
         */
        if (params != null && !params.isEmpty()) {
            headers.forEach(multipartBuilder::addFormDataPart);
        }
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::header);
        }
        Request request = requestBuilder.url(url).post(multipartBuilder.build()).build();
        LOGGER.info("post请求 => {}\n请求头：{}\n表单参数：{}", url, headers, params);
        Response response = null;
        String body = null;
        try {
            response = DEFAULT_CLIENT.newCall(request).execute();
            body = response.body() == null ? "" : response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        LOGGER.info("get响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopWatch.getTotalTimeMillis());
        return body;
    }

    /**
     * ########## 获取OkHttpClient对象 ##########
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient(Long connectTimeout, Long readTimeout, Long writeTimeout) {
        try {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            if (connectTimeout != null) {
                builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
            }
            if (readTimeout != null) {
                builder.readTimeout(readTimeout, TimeUnit.SECONDS);
            }
            if (writeTimeout != null) {
                builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
            }

            /*
             * 证书
             */
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {}

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            /*
             * 去除ssl校验
             */
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[] {x509TrustManager}, new SecureRandom());
            SSLSocketFactory ssfFactory = sc.getSocketFactory();

            /*
             * 去除ssl校验
             */
            HostnameVerifier hostnameVerifier = (s, sslSession) -> true;
            return builder.sslSocketFactory(ssfFactory, x509TrustManager).hostnameVerifier(hostnameVerifier).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
