package com.exxk.mongo.control;

import com.exxk.mongo.bean.BaseUser;
import com.exxk.mongo.bean.User;
import com.exxk.mongo.server.BaseUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/baseUser")
public class BaseUserControl {

    @Autowired
    BaseUserServer baseUserServer;

    @GetMapping(value = "/findById")
    public BaseUser findById(String id) {
        return baseUserServer.findById(id);
    }

    @PostMapping(value = "/save")
    public BaseUser save(@RequestBody BaseUser user) {
        return baseUserServer.save(user);
    }

    @DeleteMapping(value = "/delete")
    public void delete(String id) {
        baseUserServer.delete(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAll() {
        baseUserServer.deleteAll();
    }

}
