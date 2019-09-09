package com.hb.unic.cache.service;

import java.util.Set;

public interface ICacheService {

    void set(String key, Object value);

    void set(String key, Object value, long expireTime);

    String get(String key);

    Boolean delete(String key);

    Boolean hasKey(String key);

    Long getExpire(String key);

    Long getNextValue(String key);

    void set_add(String key, String... vs);

    Set<String> set_getAll(String key);

    boolean set_contains(String key, String value);
}
