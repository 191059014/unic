package com.hb.unic.util.easybuild;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 条件集合工具类
 *
 * @author Mr.Huang
 * @version v0.1, SetBuilder.java, 2020/5/25 15:18, create by huangbiao.
 */
public class SetBuilder {

    /**
     * 内部真实list
     */
    private Set<Object> realSet;

    /**
     * 构造方法
     */
    private SetBuilder() {
        this.realSet = new HashSet<>(8);
    }

    /**
     * 创建对象
     *
     * @return SetBuilder
     */
    public static SetBuilder build() {
        return new SetBuilder();
    }

    /**
     * 添加元素
     *
     * @param elements 元素集合
     * @return SetBuilder
     */
    public SetBuilder add(Object... elements) {
        realSet.addAll(Arrays.asList(elements));
        return this;
    }

    /**
     * 获取真实set
     *
     * @return List<T>
     */
    public Set<Object> get() {
        return this.realSet;
    }

    /**
     * 获取真实set
     *
     * @param elementClass 元素类型
     * @return List<T>
     */
    public <T> Set<T> get(Class<T> elementClass) {
        return (Set<T>) this.realSet;
    }

}

    