package com.exxk.mongo.dao;

import org.springframework.cache.annotation.CacheConfig;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@CacheConfig(cacheNames = {"mongo"})
public @interface CustomizeCacheConfig {
}
