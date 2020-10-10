package com.hb.unic.util.util;

import com.hb.unic.util.tool.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 生成唯一key工具类
 *
 * @version v0.1, 2020/9/1 16:17, create by huangbiao.
 */
public class KeyUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyUtils.class);

    /**
     * 生成唯一key
     *
     * @param pkFlag key标识
     * @return 标识+yyMMddHHmmssSSS（15位）+随机数（6位）
     */
    public static String getUniqueKey(String pkFlag) {
        return getNowTimeKey(pkFlag, DateUtils.FORMAT_12, 6);
    }

    /**
     * pkFlag+时间戳+随机数
     */
    public static String getNowTimeKey(String pkFlag, String format, int randomLength) {
        StringBuilder stringBuilder = new StringBuilder();
        if (pkFlag != null) {
            stringBuilder.append(pkFlag);
        }
        stringBuilder.append(DateUtils.getNowTimeStr(format));
        if (randomLength > 0) {
            stringBuilder.append(RandomUtils.getRandomOfLength(randomLength));
        }
        return stringBuilder.toString();
    }

    /**
     * 通过id和库数量获得库索引
     *
     * @param sid     分片键
     * @param dbCount 库数量
     * @return 库索引
     */
    public static long getDbIndexByAsc(Object sid, String dbCount) {
        Assert.notNull(sid, "sid is null");
        Assert.hasText(dbCount, "dbCount is empty");
        long index = getIndexByAsc(Long.parseLong(dbCount), sid);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("sid={}, dbCount={}, dbIndex={}", index, dbCount, index);
        }
        return index;
    }

    /**
     * 通过id和库数量获得表索引
     *
     * @param sid     分片键
     * @param tbCount 表数量
     * @return 表索引
     */
    public static long getTbIndexByAsc(Object sid, String tbCount) {
        Assert.notNull(sid, "sid is null");
        Assert.hasText(tbCount, "tbCount is empty");
        long index = getIndexByAsc(Long.parseLong(tbCount), sid);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("sid={}, tbCount={}, tbIndex={}", sid, tbCount, index);
        }
        return index;
    }

    /**
     * 通过asc码和库/表总数量获取索引
     *
     * @param num 库/表总数量
     * @param obj asc码
     * @return 索引
     */
    private static long getIndexByAsc(long num, Object obj) {
        String str = StrUtils.getAscII(obj == null ? "" : obj.toString());
        BigDecimal bc = new BigDecimal(str);
        BigDecimal[] results = bc.divideAndRemainder(new BigDecimal(num));
        return (long) results[1].intValue();
    }

}

    