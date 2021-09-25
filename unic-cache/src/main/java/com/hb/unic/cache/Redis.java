package com.hb.unic.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * redis操作
 *
 * @version v0.1, 2021/9/25 12:16, create by huangbiao.
 */
public class Redis implements InitializingBean {

    /**
     * 字符串类型redis操作类
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static StringRedisTemplate stringRedisTemplateAgent;

    /**
     * Object类型redis操作类
     */
    @Resource(name = "objectRedisTemplate")
    private RedisTemplate<String, Object> objectRedisTemplate;
    private static RedisTemplate<String, Object> objectRedisTemplateAgent;

    /**
     * Integer类型redis操作类
     */
    @Resource(name = "integerRedisTemplate")
    private RedisTemplate<String, Integer> integerRedisTemplate;
    private static RedisTemplate<String, Integer> integerRedisTemplateAgent;

    /**
     * Long类型redis操作类
     */
    @Resource(name = "longRedisTemplate")
    private RedisTemplate<String, Long> longRedisTemplate;
    private static RedisTemplate<String, Long> longRedisTemplateAgent;

    @Override
    public void afterPropertiesSet() throws Exception {
        stringRedisTemplateAgent = stringRedisTemplate;
        objectRedisTemplateAgent = objectRedisTemplate;
        integerRedisTemplateAgent = integerRedisTemplate;
        longRedisTemplateAgent = longRedisTemplate;
    }

    /**
     * 字符串类型
     * 
     * @return RedisTemplate
     */
    public static StringRedisTemplate strOps() {
        return stringRedisTemplateAgent;
    }

    /**
     * Object类型
     * 
     * @return RedisTemplate
     */
    public static RedisTemplate<String, Object> objOps() {
        return objectRedisTemplateAgent;
    }

    /**
     * Integer类型
     * 
     * @return RedisTemplate
     */
    public static RedisTemplate<String, Integer> intOps() {
        return integerRedisTemplateAgent;
    }

    /**
     * Long类型
     *
     * @return RedisTemplate
     */
    public static RedisTemplate<String, Long> longOps() {
        return longRedisTemplateAgent;
    }

}
