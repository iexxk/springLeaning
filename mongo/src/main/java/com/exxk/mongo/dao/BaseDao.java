package com.exxk.mongo.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * 数据库接口层
 *
 * @param <T>
 * @param <ID>
 */
@CacheConfig(cacheNames = {"mongo"})
public interface BaseDao<T, ID> {

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @Cacheable(key = "#root.target.table+#p0",condition ="#root.target.isCache")
    T findById(ID id);

    /**
     * 删除所有数据
     */
    @CacheEvict(key = "#root.target.table+'*'",condition ="#root.target.isCache") //删除所有是删除mongo所有的表，粒度不能到key
    void deleteAll();


    /**
     * 根据id删除
     *
     * @param id
     */
    @CacheEvict(key = "#root.target.table+#p0",condition ="#root.target.isCache")
    void deleteById(ID id);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @CachePut(key = "#root.target.table+#p0.id",condition ="#root.target.isCache")
    <S extends T> S save(S entity);

    /**
     * 根据实体查询
     *
     * @return
     */
    T findOne(T entity);

    void enableCache(boolean isCache);
}
