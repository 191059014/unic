package com.hb.unic.util.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ========== 日期工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.DateUtils.java, v1.0
 * @date 2019年06月05日 15时02分
 */
public class DateUtils {

    /**
     * the constant logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 默认的日期格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认的日期格式
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 毫秒格式
     */
    public static final String FORMAT_MS = "yyMMddHHmmssSSS";

    /**
     * 年月日时分秒格式
     */
    public static final String FORMAT_YMDHMS = "yyMMddHHmmss";

    /**
     * 年月日格式
     */
    public static final String FORMAT_YMD = "yyMMdd";

    /**
     * 默认的时区
     */
    public static final String DEFAULT_TIMEZONE = "GMT+8";

    /**
     * ########## 获取当前时间 ##########
     *
     * @return 当前时间
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * ########## 获取当前时间字符串 ##########
     *
     * @return 当前时间字符串
     */
    public static String getCurrentDateStr(String format) {
        return date2str(new Date(), format);
    }

    /**
     * ########## 字符串转日期 ##########
     *
     * @param dateValue  日期字符串
     * @param dateFormat 日期格式
     * @return 日期
     */
    public static Date str2date(String dateValue, String dateFormat) {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
        try {
            return dateParser.parse(dateValue);
        } catch (ParseException e) {
            LOGGER.error("str2date occur error: {}", e);
            return null;
        }
    }

    /**
     * ########## 日期转字符串 ##########
     *
     * @param date       日期
     * @param dateFormat 字符串格式
     * @return 字符串
     */
    public static String date2str(Date date, String dateFormat) {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
        return dateParser.format(date);
    }

    /**
     * 取得日期a和b间隔的天数
     *
     * @param a the a
     * @param b the b
     * @return days between
     */
    public static int getDaysBetween(Date a, Date b) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        int days = (int) (mSeconds / 86400000);
        if (mSeconds % 86400000 > 0) {
            days++;
        }
        return days;
    }

    /**
     * 判断是不是周末
     *
     * @param date 日期
     * @return boolean
     */
    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return Calendar.SATURDAY == week || Calendar.SUNDAY == week;
    }

    /**
     * 给日期增加天数
     *
     * @param date   日期
     * @param addNum 增加的天数
     * @return 增加天数后的日期
     */
    public static Date addDays(Date date, int addNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addNum);
        return calendar.getTime();
    }

    /**
     * ########## 日期格式辅助类 ##########
     */
    static final class DateFormatHolder {
        /**
         * 线程私有的
         */
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = ThreadLocal.withInitial(() -> new SoftReference(new HashMap()));

        /**
         * ########## 获取SimpleDateFormat对象 ##########
         *
         * @param pattern 格式
         * @return SimpleDateFormat
         */
        static SimpleDateFormat formatFor(String pattern) {
            SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
            Map<String, SimpleDateFormat> formats = ref.get();
            if (formats == null) {
                formats = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference(formats));
            }
            SimpleDateFormat format = formats.get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern);
                formats.put(pattern, format);
            }
            return format;
        }
    }

    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.add(Calendar.DATE, 0);
        int daysBetween = getDaysBetween(c2.getTime(), c1.getTime());
        System.out.println(daysBetween);
    }

}
