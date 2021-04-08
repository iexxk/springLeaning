package com.exxk.mongo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration

public class CachingConfig {
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("table");
//    }
}
