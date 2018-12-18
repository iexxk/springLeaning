package com.exxk.aop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop/")
public class AopController {

    @GetMapping("test")
    public String aop(){
        return "aop 测试";
    }

}
