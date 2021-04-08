package com.exxk.mongo.control;

import com.exxk.mongo.bean.User;
import com.exxk.mongo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserControl {

    @Autowired
    UserServer userServer;

    @GetMapping(value = "/findById")
    public User findById(String id) {
        return userServer.findById(id);
    }

    @PostMapping(value = "/save")
    public User save(@RequestBody User user) {
        return userServer.save(user);
    }

    @DeleteMapping(value = "/delete")
    public void delete(String id) {
        userServer.delete(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAll() {
        userServer.deleteAll();
    }

    @GetMapping(value = "/update")
    public void update(String id) {
         userServer.update(id);
    }

}
