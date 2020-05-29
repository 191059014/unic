package com.hb.unic.util.model;

import java.util.List;

/**
 * 分页对象
 *
 * @author Mr.huang
 * @since 2020/5/8 16:25
 */
public class Page<T> {

    // 总条数
    private int count;
    // 开始行
    private int startRows;
    // 每页条数
    private int pageSize;
    // 数据集合
    private List<T> data;

    public Page(int count, int startRows, int pageSize, List<T> data) {
        this.count = count;
        this.startRows = startRows;
        this.pageSize = pageSize;
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartRows() {
        return startRows;
    }

    public void setStartRows(int startRows) {
        this.startRows = startRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{"
                + "\"count\":"
                + count
                + ",\"startRows\":"
                + startRows
                + ",\"pageSize\":"
                + pageSize
                + ",\"data\":"
                + data
                + "}";
    }

}
