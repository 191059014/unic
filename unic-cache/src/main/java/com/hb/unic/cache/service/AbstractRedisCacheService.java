package com.hb.unic.cache.service;

import com.hb.unic.cache.ICacheService;

import java.util.Set;

/**
 * redis缓存抽象类
 *
 * @author Mr.huang
 * @since 2020/5/6 10:00
 */
public abstract class AbstractRedisCacheService implements ICacheService {

    /**
     * 添加缓存并设置过期时间
     *
     * @param key        缓存key
     * @param value      缓存值
     * @param expireTime 过期时间
     */
    protected abstract void set(String key, Object value, long expireTime);

    /**
     * 获取某个key的过期时间
     *
     * @param key 缓存key
     * @return 过期时间
     */
    protected abstract Long getExpire(String key);

    /**
     * 缓存值递增
     *
     * @param key 缓存key
     * @return 下一个值
     */
    protected abstract Long increment(String key);

    /**
     * set类型添加
     *
     * @param key 缓存key
     * @param vs  元素数组
     */
    protected abstract void set_add(String key, String... vs);

    /**
     * set类型获取所有值
     *
     * @param key 缓存key
     * @return 结果
     */
    protected abstract Set<String> set_getAll(String key);

    /**
     * set类型是否包含
     *
     * @param key   缓存key
     * @param value 值
     * @return true为包含
     */
    protected abstract boolean set_contains(String key, String value);

}
