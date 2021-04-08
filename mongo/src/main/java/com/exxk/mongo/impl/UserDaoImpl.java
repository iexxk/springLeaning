package com.exxk.mongo.impl;

import com.exxk.mongo.bean.User;
import com.exxk.mongo.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super.enableCache(true);
    }

    @Override
    public void updateAddNumById(String id) {
        System.out.println("aaa"+id);
    }

}
