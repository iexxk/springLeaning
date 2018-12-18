package com.exxk.aop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/aop/")
public class AopController {
    private Logger logger=Logger.getLogger(String.valueOf(getClass()));
    @GetMapping("test")
    public String aop(){
        logger.info("业务代码执行中...");
        return "aop 测试";
    }
}
