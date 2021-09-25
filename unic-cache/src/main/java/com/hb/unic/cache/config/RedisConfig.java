package com.hb.unic.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * 装配redis操作类
 *
 * @version v0.1, 2021/9/25 12:20, create by huangbiao.
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 创建Object类型的RedisTemplate操作类
     */
    @Bean("objectRedisTemplate")
    RedisTemplate<String, Object> objectRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initRedisTemplate(redisTemplate);
        log.info("Create objectRedisTemplate complete");
        return redisTemplate;
    }

    /**
     * 创建Integer类型的RedisTemplate操作类
     */
    @Bean("integerRedisTemplate")
    RedisTemplate<String, Integer> integerRedisTemplate() {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        initRedisTemplate(redisTemplate);
        log.info("Create integerRedisTemplate complete");
        return redisTemplate;
    }

    /**
     * 创建Long类型的RedisTemplate操作类
     */
    @Bean("longRedisTemplate")
    RedisTemplate<String, Long> longRedisTemplate() {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        initRedisTemplate(redisTemplate);
        log.info("Create longRedisTemplate complete");
        return redisTemplate;
    }

    /**
     * 初始化redisTemplate对象
     *
     * @param redisTemplate
     *            redisTemplate对象
     */
    public void initRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        redisTemplate.setValueSerializer(serializer);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
    }

}
