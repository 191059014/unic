package com.hb.unic.cache.service;

public interface ICacheService {

    void set(String key, Object value);

    void set(String key, Object value, long expireTime);

    String get(String key);

    Boolean delete(String key);

    Boolean hasKey(String key);

    Long getExpire(String key);

    Long getNextValue(String key);

}
