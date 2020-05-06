package com.hb.unic.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.hb.unic.cache.ICacheService;
import com.hb.unic.cache.service.AbstractRedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ========== redis工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.RedisTools.java, v1.0
 * @date 2019年06月11日 00时19分
 */
@Primary
@Component("redisCacheService")
public class RedisCacheServiceImpl extends AbstractRedisCacheService implements ICacheService {

    /**
     * 自定义 log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    /**
     * 时间单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 字符串redis操作工具
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * ########## 放入缓存 ##########
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void set(String key, Object value) {
        String valueStr = JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, valueStr);
    }

    /**
     * ########## 放入缓存 ##########
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     */
    @Override
    public void set(String key, Object value, long expireTime) {
        String valueStr = JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, valueStr, expireTime, TIME_UNIT);
    }

    /**
     * ########## 从缓存获取 ##########
     *
     * @param key 键
     */
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * ########## 删除缓存 ##########
     *
     * @param key 键
     */
    @Override
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * ########## 判断缓存是否存在 ##########
     *
     * @param key 键
     */
    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * ########## 根据key获取过期时间 ##########
     *
     * @param key 键
     */
    @Override
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TIME_UNIT);
    }

    /**
     * ########## 根据key获取下一个值 ##########
     *
     * @param key 键
     */
    @Override
    public Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    /**
     * 操作set集合，增加元素
     *
     * @param key 键
     * @param vs  添加的元素数组
     */
    @Override
    public void set_add(String key, String... vs) {
        stringRedisTemplate.opsForSet().add(key, vs);
    }

    /**
     * 操作set集合，获取所有元素
     *
     * @param key 键
     * @return 所有元素集合
     */
    @Override
    public Set<String> set_getAll(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 操作set集合，判断是否包含某个值
     *
     * @param key   键
     * @param value 值
     * @return 是否包含
     */
    @Override
    public boolean set_contains(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

}