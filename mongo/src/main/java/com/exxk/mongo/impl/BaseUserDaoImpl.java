package com.exxk.mongo.impl;

import com.exxk.mongo.bean.BaseUser;
import com.exxk.mongo.dao.BaseUserDao;
import org.springframework.stereotype.Repository;


@Repository
public class BaseUserDaoImpl extends BaseDaoImpl<BaseUser, String> implements BaseUserDao {

//    public BaseUserDaoImpl() {
//        super.enableCache(false);
//    }


}
