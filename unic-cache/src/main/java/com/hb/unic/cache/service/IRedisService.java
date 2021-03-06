package com.hb.unic.cache.service;

import java.util.List;
import java.util.Map;

public interface IRedisService {

    /**
     * 添加缓存并设置过期时间
     *
     * @param key          缓存key
     * @param value        缓存值
     * @param expireSecond 过期时间
     */
    void set(String key, Object value, long expireSecond);

    /**
     * 获取Object类型缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    String get(String key);

    /**
     * 获取Bean类型缓存，注意：bean里面不能包含复杂类型
     *
     * @param key 缓存key
     * @return 缓存值
     */
    <T> T getBean(String key, Class<T> tClass);

    /**
     * 获取Integer类型缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    Integer getInteger(String key);

    /**
     * 获取Long类型缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    Long getLong(String key);

    /**
     * 获取Map类型缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    Map<String, Object> getMap(String key);

    /**
     * 获取List类型缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    List<Object> getList(String key);

    /**
     * 获取某个key的过期时间，单位为秒
     *
     * @param key 缓存key
     * @return 过期时间
     */
    Long getExpire(String key);

    /**
     * 设置某个key的过期时间，单位为秒
     *
     * @param key  缓存key
     * @param time 过期时间
     * @return 结果
     */
    boolean setExpire(String key, long time);

    /**
     * 缓存值递增
     *
     * @param key 缓存key
     * @return 下一个值
     */
    Long increment(String key);

    /**
     * 缓存值按指定值递增
     *
     * @param key 缓存key
     * @return 下一个值
     */
    Long incrementBy(String key, long incrmentNum);

    /**
     * 删除缓存
     *
     * @param key 缓存key
     * @return true为删除成功，false为删除失败
     */
    Boolean delete(String key);

    /**
     * 是否包含某个key
     *
     * @param key 缓存key
     * @return true为包含
     */
    Boolean hasKey(String key);

}
