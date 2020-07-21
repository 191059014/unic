package com.hb.unic.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.Map;

/**
 * redis配置
 *
 * @version v0.1, 2020/7/21 16:19, create by huangbiao.
 */
@Configuration
@ConditionalOnClass(RedisTemplate.class)
@ConditionalOnBean(RedisConnectionFactory.class)
public class RedisConfig {

    /**
     * 创建Object类型的RedisTemplate操作类
     */
    @Bean("objectRedisTemplate")
    RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        return initRedisTemplate(redisTemplate, redisConnectionFactory);
    }

    /**
     * 创建Integer类型的RedisTemplate操作类
     */
    @Bean("integerRedisTemplate")
    RedisTemplate<String, Integer> integerRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        return initRedisTemplate(redisTemplate, redisConnectionFactory);
    }

    /**
     * 创建Long类型的RedisTemplate操作类
     */
    @Bean("longRedisTemplate")
    RedisTemplate<String, Long> longRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        return initRedisTemplate(redisTemplate, redisConnectionFactory);
    }

    /**
     * 创建Map类型的RedisTemplate操作类
     */
    @Bean("mapRedisTemplate")
    RedisTemplate<String, Map<String, Object>> mapRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Map<String, Object>> redisTemplate = new RedisTemplate<>();
        return initRedisTemplate(redisTemplate, redisConnectionFactory);
    }

    /**
     * 创建List类型的RedisTemplate操作类
     */
    @Bean("listRedisTemplate")
    RedisTemplate<String, List<Object>> listRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, List<Object>> redisTemplate = new RedisTemplate<>();
        return initRedisTemplate(redisTemplate, redisConnectionFactory);
    }

    /**
     * 初始化redisTemplate对象
     *
     * @param redisTemplate          redisTemplate对象
     * @param redisConnectionFactory redis连接工厂
     * @return RedisTemplate
     */
    private RedisTemplate initRedisTemplate(RedisTemplate redisTemplate, RedisConnectionFactory redisConnectionFactory) {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        redisTemplate.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}

    