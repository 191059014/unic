package com.hb.unic.cache;

public interface ICacheService {

    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    void set(String key, Object value);

    /**
     * 获取缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    String get(String key);

    /**
     * 删除缓存
     *
     * @param key 缓存key
     * @return true为删除成功，false为删除失败
     */
    Boolean delete(String key);

    /**
     * 是否包含某个key
     *
     * @param key 缓存key
     * @return true为包含
     */
    Boolean hasKey(String key);

}
