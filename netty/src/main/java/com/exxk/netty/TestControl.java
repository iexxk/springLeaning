package com.exxk.netty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestControl {

    @GetMapping("/hello")
    public String test(){
        //8080访问
        return "hello world";
    }


}
