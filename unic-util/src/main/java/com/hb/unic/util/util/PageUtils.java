package com.hb.unic.util.util;

import com.hb.unic.util.model.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ========== 分页工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.PageUtils.java, v1.0
 * @date 2019年06月23日 00时38分
 */
public class PageUtils {

    /**
     * ########## 计算起始记录行数 ##########
     *
     * @param pageNum  当前第几页
     * @param pageSize 每页多少条
     * @return 起始记录行数
     */
    public static Integer getStartRow(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 物理分页
     *
     * @param tList    数据集合
     * @param pageNum  第几页
     * @param pageSize 每页条数
     * @param <T>      数据类型
     * @return 分页结果
     */
    public static <T> Page<T> pagination(List<T> tList, int pageNum, int pageSize) {
        Integer startRow = getStartRow(pageNum, pageSize);
        List<T> list = tList.stream().skip(startRow).limit(pageSize).collect(Collectors.toList());
        return new Page<>(tList.size(), startRow, pageSize, list);
    }

}
