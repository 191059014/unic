package com.hb.unic.util.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页
 *
 * @version v0.1, 2020/7/31 13:15, create by huangbiao.
 */
public class Pagination<T> {

    /**
     * 数据集合
     */
    private List<T> data;
    /**
     * 总条数
     */
    private long count;
    /**
     * 当前页数
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 无参构造
     */
    public Pagination() {
    }

    /**
     * 全参构造
     */
    public Pagination(List<T> data, long count, int pageNum, int pageSize) {
        this.data = data;
        this.count = count;
        this.pageNum = pageNum;
        if (pageSize > 100)
            this.pageSize = 100;
        else
            this.pageSize = pageSize;
    }

    /**
     * ########## 计算起始记录行数 ##########
     *
     * @param pageNum  当前第几页
     * @param pageSize 每页多少条
     * @return 起始记录行数
     */
    public static int getStartRow(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 物理分页
     *
     * @param tList    数据集合
     * @param pageNum  第几页
     * @param pageSize 每页条数
     * @param <E>      数据类型
     * @return 分页结果
     */
    public static <E> Pagination<E> pagination(List<E> tList, int pageNum, int pageSize) {
        Integer startRow = getStartRow(pageNum, pageSize);
        List<E> list = tList.stream().skip(startRow).limit(pageSize).collect(Collectors.toList());
        return new Pagination<>(list, tList.size(), startRow, pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

    