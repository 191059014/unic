package com.hb.unic.util.util;

import com.google.common.base.Stopwatch;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    private static final OkHttpClient CLIENT = getOkHttpClient(15L, 15L, 15L);

    /**
     * ########## get请求 ##########
     *
     * @param url
     *            请求ur
     * @return response的body信息
     */
    public static String get(String url) throws Exception {
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
    public static String doGet(String url, Map<String, String> headers) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.get().build();
        LOGGER.info("get请求 => {}\n请求头：{}", url, headers);
        Call call = CLIENT.newCall(request);
        Response response = call.execute();
        String body = response.body() == null ? "" : response.body().string();
        LOGGER.info("get响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopwatch.elapsed(TimeUnit.MILLISECONDS));
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
    public static String postJson(String url, Object reqBody) throws Exception {
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
    public static String postJson(String url, Object reqBody, Map<String, String> headers) throws Exception {
        return doPostJsonOrXml(url, JsonUtils.toJson(reqBody), MediaType.get(JSON), headers);
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
    public static String postXml(String url, String xml) throws Exception {
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
    public static String postXml(String url, String xml, Map<String, String> headers) throws Exception {
        return doPostJsonOrXml(url, xml, MediaType.get(XML), headers);
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
    public static String doPostJsonOrXml(String url, String reqBody, MediaType mediaType, Map<String, String> headers)
        throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RequestBody requestBody = FormBody.create(mediaType, reqBody);
        Request.Builder builder = new Request.Builder().url(url).addHeader(CONTENT_TYPE, JSON);
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
        Request request = builder.post(requestBody).build();
        LOGGER.info("post请求 => {}\n请求头：{}\n请求体：{}", url, headers, reqBody);
        Call call = CLIENT.newCall(request);
        Response response = call.execute();
        String body = response.body() == null ? "" : response.body().string();
        LOGGER.info("post响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopwatch.elapsed(TimeUnit.MILLISECONDS));
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
    public static String postForm(String url, Map<String, String> headers, Map<String, String> params)
            throws Exception {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (params != null && !params.isEmpty()) {
            headers.forEach(multipartBuilder::addFormDataPart);
        }
        return doPostForm(url, headers, multipartBuilder);
    }

    /**
     * form方式
     *
     * @param url
     *            url
     * @param headers
     *            请求头
     * @return 结果
     */
    public static String doPostForm(String url, Map<String, String> headers, MultipartBody.Builder multipartBuilder) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::header);
        }
        Request request = requestBuilder.url(url).post(multipartBuilder.build()).build();
        LOGGER.info("post请求 => {}\n请求头：{}", url, headers);
        Response response = CLIENT.newCall(request).execute();
        String body = response.body() == null ? "" : response.body().string();
        LOGGER.info("post响应 => {}\n响应结果：{}\n总共耗时：{}ms", url, body, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return body;
    }

    /**
     * Multipart类型参数构造器
     */
    public static class MultipartBodyBuilder {

        MultipartBody.Builder mb = new MultipartBody.Builder().setType(MultipartBody.FORM);

        /**
         * 创建对象
         */
        public static MultipartBodyBuilder create() {
            return new MultipartBodyBuilder();
        }

        /**
         * 添加普通的form参数
         */
        public MultipartBodyBuilder add(String key, String value) {
            mb.addFormDataPart(key, value);
            return this;
        }

        /**
         * 添加普通的form参数
         */
        public MultipartBodyBuilder add(Map<String, String> params) {
            if (params != null && !params.isEmpty()) {
                params.forEach(mb::addFormDataPart);
            }
            return this;
        }

        /**
         * 普通文件参数
         */
        public MultipartBodyBuilder addFile(String fileParamName, String fileName, String fileText) {
            Objects.requireNonNull(fileParamName, "fileParamName is null");
            Objects.requireNonNull(fileName, "fileName is null");
            Objects.requireNonNull(fileText, "fileText is null");
            mb.addFormDataPart(fileParamName, fileName,
                    RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), fileText.getBytes()));
            return this;
        }

        /**
         * zip文件参数
         */
        public MultipartBodyBuilder addZip(String fileParamName, String fileName, String zipEntryName, String fileText) throws Exception {
            Objects.requireNonNull(fileParamName, "fileParamName is null");
            Objects.requireNonNull(fileName, "fileName is null");
            Objects.requireNonNull(zipEntryName, "zipEntryName is null");
            Objects.requireNonNull(fileText, "fileText is null");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(bos);
            ZipEntry entry = new ZipEntry(zipEntryName);
            zip.putNextEntry(entry);
            zip.write(fileText.getBytes());
            zip.closeEntry();
            zip.close();
            byte[] b = bos.toByteArray();
            bos.close();
            mb.addFormDataPart(fileParamName, fileName,
                    RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), b));
            return this;
        }

        /**
         * 获取MultipartBody.Builder对象
         */
        public MultipartBody.Builder get() {
            return mb;
        }

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
            e.printStackTrace();
            return null;
        }

    }

}
