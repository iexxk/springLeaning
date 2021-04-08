package com.exxk.mongo.config;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

public class CustomizedRedisCache extends RedisCache {
    private final String name;
    private final RedisCacheWriter cacheWriter;
    private final ConversionService conversionService;

    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected CustomizedRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
        this.name = name;
        this.cacheWriter = cacheWriter;
        this.conversionService = cacheConfig.getConversionService();
    }

    @Override
    public void evict(Object key) {
        if (key instanceof String) {
            String keyString = key.toString();
            // 后缀删除
            if (keyString.endsWith("*")) {
                byte[] pattern = this.conversionService.convert(this.createCacheKey(key), byte[].class);
                this.cacheWriter.clean(this.name, pattern);
                return;
            }
        }
        // 删除指定的key
        super.evict(key);
    }
}
