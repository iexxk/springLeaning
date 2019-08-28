package com.exxk.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;


@Document(collection = "user_info")
public class User {
    @Id
    private String id;

    @Indexed()
    @Field("num")
    private String num;

    @Field("user_map")
    private Map<String,BaseUser> userMap;

    @Field("user_list")
    private List<BaseUser> userList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Map<String, BaseUser> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, BaseUser> userMap) {
        this.userMap = userMap;
    }

    public List<BaseUser> getUserList() {
        return userList;
    }

    public void setUserList(List<BaseUser> userList) {
        this.userList = userList;
    }
}
