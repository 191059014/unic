package com.hb.unic.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.hb.unic.cache.service.ICacheService;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ========== redis工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.RedisTools.java, v1.0
 * @date 2019年06月11日 00时19分
 */
@Component("redisCacheService")
public class RedisCacheServiceImpl implements ICacheService {

    /**
     * 自定义 log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

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
    public Long getNextValue(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

}