package com.exxk.aop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/aop/")
public class AopController {
    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @GetMapping("test")
    @Log(tag = "我是注解")
    public String aop() {
        logger.info("业务代码执行中...");

        double bj = 10000;
        double db = 1000;
        double ds = 0.01;
        int day = 100;
        double dz;
        double tr = bj;
        for (int i = 0; i < day; i++) {
            dz = Math.random() > 0.5 ? 1 : -1;
            Random random = new Random();
            ds = random.nextInt(10) * dz;
            logger.info("随机点：" + ds / 100);
            bj = bj + (bj * ds / 100) - db * dz;
            tr = tr - db * dz;
            logger.info("总资产：" + bj + "，总投入：" + tr);
        }
        return "aop 测试";
    }


    @GetMapping("simpleLog")
    @SimpleLog
    public String simpleLog() {
        logger.info("注解包裹 重写");
        return "注解包裹 重写";
    }


}
