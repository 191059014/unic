package com.hb.unic.util.util;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.util.LogExceptionWapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ========== 日期工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.DateUtils.java, v1.0
 * @date 2019年06月05日 15时02分
 */
public class DateUtils {

    /**
     * the constant LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 格式化
     */
    public static final String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化
     */
    public static final String FORMAT_2 = "yyyy-MM-dd";

    /**
     * 格式化
     */
    public static final String FORMAT_3 = "yyyy-MM";

    /**
     * 格式化
     */
    public static final String FORMAT_4 = "yyyyMMddHHmmss";

    /**
     * 格式化
     */
    public static final String FORMAT_5 = "yyyyMMdd";

    /**
     * 格式化
     */
    public static final String FORMAT_6 = "yyyyMM";

    /**
     * 格式化
     */
    public static final String FORMAT_7 = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 毫秒格式
     */
    public static final String FORMAT_8 = "yyyyMMddHHmmssSSS";

    /**
     * 最大日期
     */
    private static Date maxDate;
    /**
     * 最大时间戳
     */
    private static Timestamp maxTimestamp;

    static {
        try {
            maxDate = new StdDateFormat().parse("9999-12-31T23:59:59.999+0800");
            maxTimestamp = new Timestamp(maxDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取最大日期
     *
     * @return 最大日期
     */
    public static Date getMaxDate() {
        return maxDate;
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return 毫秒数
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的纳秒数
     *
     * @return 纳秒数
     */
    public static long getCurrentNanoTime() {
        return System.nanoTime();
    }

    /**
     * ########## 获取当前时间 ##########
     *
     * @return 当前时间
     */
    public static Date getNowTime() {
        return new Date();
    }

    /**
     * ########## 获取当前时间字符串 ##########
     *
     * @return 当前时间字符串
     */
    public static String getNowTime(String format) {
        return date2str(new Date(), format);
    }

    /**
     * 获取指定时间的日历
     *
     * @param date 日期
     * @return 日历
     */
    public static Calendar getCalendar(Date date) {
        if (date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取当前时间的日历
     *
     * @return 当前时间的日历
     */
    public static Calendar getCurrentCalendar() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * ########## 字符串转日期 ##########
     *
     * @param dateValue  日期字符串
     * @param dateFormat 日期格式
     * @return 日期
     */
    public static Date str2date(String dateValue, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).parse(dateValue);
        } catch (ParseException e) {
            LOGGER.error("str2date occur error: {}", LogExceptionWapper.getStackTrace(e));
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
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * 获取两个日期相差的年数
     *
     * @param end   结束日期
     * @param start 开始日期
     * @return 相差的年数
     */
    public static int getYearsBetween(Date end, Date start) {
        Calendar endCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        startCalendar.setTime(start);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if ((endCalendar.get(Calendar.MONTH) < startCalendar.get(Calendar.MONTH))
                || ((endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) && (endCalendar.get(Calendar.DAY_OF_MONTH) < startCalendar.get(Calendar.DAY_OF_MONTH))))
            year--;
        return year;
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param end         结束日期
     * @param start       开始日期
     * @param compareDays 是否比较天数
     * @return 相差的月数
     */
    public static int getMonthsBetween(Date end, Date start, boolean compareDays) {
        Calendar endCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        startCalendar.setTime(start);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        month = year * 12 + month;
        if (compareDays) {
            int endDayOfMonth = endCalendar.get(Calendar.DAY_OF_MONTH);
            int startDayOfMonth = startCalendar.get(Calendar.DAY_OF_MONTH);
            if (endDayOfMonth < startDayOfMonth)
                month--;
            else if (endDayOfMonth > startDayOfMonth)
                month++;
        }
        return month;
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
    public static Date addDay(Date date, int addNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, addNum);
        return calendar.getTime();
    }

    /**
     * 给日期增加月数
     *
     * @param date   日期
     * @param addNum 增加的月数
     * @return 增加月数后的日期
     */
    public static Date addMonth(Date date, int addNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addNum);
        return calendar.getTime();
    }

    /**
     * 给日期增加年数
     *
     * @param date   日期
     * @param addNum 增加的年数
     * @return 增加年数后的日期
     */
    public static Date addYear(Date date, int addNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, addNum);
        return calendar.getTime();
    }

    /**
     * 获取年份
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取天数
     *
     * @param date 日期
     * @return 天数
     */
    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取秒数
     *
     * @param date 日期
     * @return 秒数
     */
    public static int getSecond(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取指定日期的指定时间
     *
     * @param date 日期
     * @param time 时间字符串（HH:mm:ss）
     * @return （日期）yyyy-MM-dd HH:mm:ss
     */
    public static Date getAssignTime(Date date, String time) {
        String ymd = date2str(date, FORMAT_2);
        return str2date(ymd + " " + time, FORMAT_1);
    }

    /**
     * 获取指定日期加减n天后的指定时间
     *
     * @param date   日期
     * @param addDay 加减的天数
     * @param time   时间字符串（HH:mm:ss）
     * @return （日期）yyyy-MM-dd HH:mm:ss
     */
    public static Date getAssignDateTime(Date date, int addDay, String time) {
        return getAssignTime(addDay(date, addDay), time);
    }

    /**
     * 获取当前时间附近时间
     *
     * @return 时间
     */
    public static Date getTimeNearBy(int field, int addNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(field, addNum);
        return calendar.getTime();
    }

}
