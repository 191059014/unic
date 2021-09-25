package com.hb.unic.cache.config;

import com.hb.unic.cache.Redis;
import com.hb.unic.cache.controller.RedisController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 装配redis操作类
 *
 * @version v0.1, 2021/9/25 12:20, create by huangbiao.
 */
@Configuration
@Import({RedisConfig.class, Redis.class, RedisController.class})
public class EnableRedisAutoConfiguration {

}
