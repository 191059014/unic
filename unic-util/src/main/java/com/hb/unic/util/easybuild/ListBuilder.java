package com.hb.unic.util.easybuild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 条件集合工具类
 *
 * @author Mr.Huang
 * @version v0.1, MapBuilder.java, 2020/5/25 15:18, create by huangbiao.
 */
public class ListBuilder {

    /**
     * 内部真实list
     */
    private List realList;

    /**
     * 构造方法
     */
    private ListBuilder() {
        this.realList = new ArrayList<>(8);
    }

    /**
     * 创建对象
     *
     * @return ListBuilder
     */
    public static ListBuilder build() {
        return new ListBuilder();
    }

    /**
     * 添加元素
     *
     * @param elements 元素集合
     * @return ListBuilder
     */
    public ListBuilder add(Object... elements) {
        this.realList.addAll(Arrays.asList(elements));
        return this;
    }

    /**
     * 获取真实list
     *
     * @return List<T>
     */
    public List get() {
        return this.realList;
    }

}

    