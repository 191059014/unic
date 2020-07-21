package com.hb.unic.cache.service;

import com.hb.unic.cache.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * redis缓存抽象类
 *
 * @author Mr.huang
 * @since 2020/5/6 10:00
 */
public abstract class AbstractRedisService implements IRedisService {


}
