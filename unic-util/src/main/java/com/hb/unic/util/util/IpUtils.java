package com.hb.unic.util.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author guoll
 * @date 2020/9/20
 */
public class IpUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtils.class);

    /**
     * 请求头
     */
    private static final String[] HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };

    /**
     * 判断ip是否为空
     *
     * @param ip ip地址
     * @return 空返回true
     */
    public static boolean isEmptyIp(String ip) {
        return (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip));
    }


    /**
     * 判断ip是否不为空
     *
     * @param ip ip地址
     * @return 不为空返回true
     */
    public static boolean isNotEmptyIp(String ip) {
        return !isEmptyIp(ip);
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request HttpServletRequest
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = "";
        for (String header : HEADERS) {
            ip = request.getHeader(header);
            if (isNotEmptyIp(ip)) {
                break;
            }
        }
        if (isEmptyIp(ip)) {
            ip = request.getRemoteAddr();
        }
        if (isNotEmptyIp(ip) && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }


    /**
     * 获取本机的局域网ip地址，兼容Linux
     *
     * @return 本机的局域网ip地址
     */
    public static String getLocalHostIP() {
        String localHostAddress = "";
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> address = networkInterface.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress inetAddress = address.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        localHostAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取本机的局域网ip地址异常=", e);
            }
        }
        return localHostAddress;
    }

}
