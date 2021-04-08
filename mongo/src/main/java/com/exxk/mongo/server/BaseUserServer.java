package com.exxk.mongo.server;

import com.exxk.mongo.bean.BaseUser;
import com.exxk.mongo.bean.User;
import com.exxk.mongo.dao.BaseUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServer {

    @Autowired
    BaseUserDao baseUserDao;


    public BaseUser findById(String id) {
        return baseUserDao.findById(id);
    }

    public BaseUser save(BaseUser user) {
        return baseUserDao.save(user);
    }

    public void delete(String id) {
        baseUserDao.deleteById(id);
    }

    public void deleteAll() {
        baseUserDao.deleteAll();
    }
}
