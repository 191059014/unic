package com.hb.unic.util.easybuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 条件集合工具类
 *
 * @author Mr.Huang
 * @version v0.1, MapBuilder.java, 2020/5/25 15:18, create by huangbiao.
 */
public class MapBuilder {

    /**
     * 内部真实map
     */
    private Map<String, Object> realMap;

    /**
     * 构造方法
     */
    private MapBuilder() {
        this.realMap = new HashMap<>(8);
    }

    /**
     * 创建对象
     *
     * @return MapBuilder
     */
    public static MapBuilder build() {
        return new MapBuilder();
    }

    /**
     * 添加键值对
     *
     * @param key   key
     * @param value value
     * @return MapBuilder
     */
    public MapBuilder add(String key, Object value) {
        this.realMap.put(key, value);
        return this;
    }

    /**
     * 添加键值对
     *
     * @param key1   key1
     * @param value1 value1
     * @param key2   key2
     * @param value2 value2
     * @return MapBuilder
     */
    public MapBuilder add(String key1, Object value1, String key2, Object value2) {
        this.realMap.put(key1, value1);
        this.realMap.put(key2, value2);
        return this;
    }

    /**
     * 添加键值对
     *
     * @param key1   key1
     * @param value1 value1
     * @param key2   key2
     * @param value2 value2
     * @param key3   key3
     * @param value3 value3
     * @return MapBuilder
     */
    public MapBuilder add(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        this.realMap.put(key1, value1);
        this.realMap.put(key2, value2);
        this.realMap.put(key3, value3);
        return this;
    }

    /**
     * 获取真实map
     *
     * @return Map
     */
    public Map<String, Object> get() {
        return this.realMap;
    }

}

    