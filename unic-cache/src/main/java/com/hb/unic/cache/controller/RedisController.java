package com.hb.unic.cache.controller;

import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ResultCode;
import com.hb.unic.base.controller.BaseController;
import com.hb.unic.cache.Redis;
import com.hb.unic.common.validator.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存管理
 *
 * @version v0.1, 2021/9/21 9:45, create by huangbiao.
 */
@RestController
@RequestMapping("/cache")
public class RedisController extends BaseController {

    /**
     * 获取缓存值
     * 
     * @param key
     *            缓存key
     * @return 结果
     */
    @GetMapping("/get/{key}")
    public Result<Map<String, Object>> get(@PathVariable("key") String key) {
        Assert.hasText(key, ResultCode.PARAM_ILLEGAL);
        Map<String, Object> map = new HashMap<>();
        map.put("value", Redis.strOps().opsForValue().get(key));
        map.put("expire", Redis.strOps().getExpire(key, TimeUnit.SECONDS));
        return Result.success(map);
    }

    /**
     * 删除缓存值
     * 
     * @param key
     *            缓存key
     * @return 结果
     */
    @GetMapping("/delete/{key}")
    public Result<Boolean> delete(@PathVariable("key") String key) {
        Assert.hasText(key, ResultCode.PARAM_ILLEGAL);
        return Result.success(Redis.strOps().delete(key));
    }

}
