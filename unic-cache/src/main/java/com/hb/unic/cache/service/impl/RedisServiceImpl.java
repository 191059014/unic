package com.hb.unic.cache.service.impl;

import com.hb.unic.cache.service.IRedisService;
import com.hb.unic.cache.util.JsonUtils;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ========== redis工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.RedisTools.java, v1.0
 * @date 2019年06月11日 00时19分
 */
@Primary
@Component
@ConditionalOnClass(RedisTemplate.class)
public class RedisServiceImpl extends AbstractRedisService implements IRedisService {

    /**
     * 自定义 log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    /**
     * 字符串类型redis操作工具
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Object类型redis操作工具
     */
    @Resource(name = "objectRedisTemplate")
    private RedisTemplate<String, Object> objectRedisTemplate;

    /**
     * Integer类型redis操作工具
     */
    @Resource(name = "integerRedisTemplate")
    private RedisTemplate<String, Integer> integerRedisTemplate;

    /**
     * Object类型redis操作工具
     */
    @Resource(name = "longRedisTemplate")
    private RedisTemplate<String, Long> longRedisTemplate;

    /**
     * Map类型redis操作工具
     */
    @Resource(name = "mapRedisTemplate")
    private RedisTemplate<String, Map<String, Object>> mapRedisTemplate;

    /**
     * List类型redis操作工具
     */
    @Resource(name = "listRedisTemplate")
    private RedisTemplate<String, List<Object>> listRedisTemplate;

    @Override
    public void set(String key, Object value, long expireTime) {
        String json = JsonUtils.toJson(value);
        stringRedisTemplate.opsForValue().set(key, json, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getBean(String key, Class<T> tClass) {
        return (T) objectRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Integer getInteger(String key) {
        return integerRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long getLong(String key) {
        return longRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Map<String, Object> getMap(String key) {
        return mapRedisTemplate.opsForValue().get(key);
    }

    @Override
    public List<Object> getList(String key) {
        return listRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long incrementBy(String key, Long incrmentNum) {
        return stringRedisTemplate.opsForValue().increment(key, incrmentNum);
    }

    @Override
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

}