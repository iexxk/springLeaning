package com.exxk.mongo.dao;

import com.exxk.mongo.bean.User;
import org.springframework.cache.annotation.CacheEvict;


public interface UserDao extends BaseDao<User, String> {

    @CacheEvict(key = "#root.target.table+#p0",condition ="#root.target.isCache")
    void updateAddNumById(String id);
}
