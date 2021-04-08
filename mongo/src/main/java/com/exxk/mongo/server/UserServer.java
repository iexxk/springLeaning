package com.exxk.mongo.server;

import com.exxk.mongo.bean.User;
import com.exxk.mongo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServer {

    @Autowired
    UserDao userDao;


    public User findById(String id) {
        return userDao.findById(id);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void delete(String id) {
        userDao.deleteById(id);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public void update(String id) {
         userDao.updateAddNumById(id);
    }
}
