package com.exxk.rbmq;

import cn.hutool.extra.spring.SpringUtil;
import javafx.application.Application;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class RbController {


    @PostMapping
    public String addQueue(@RequestParam String queueNmae){
        SimpleMessageListenerContainer container= SpringUtil.getBean(SimpleMessageListenerContainer.class);//获取实例
        container.addQueueNames(queueNmae);
        return "add "+queueNmae+" ok";
    }

    @DeleteMapping
    public String delQueue(@RequestParam String queueNmae){
        SimpleMessageListenerContainer container= SpringUtil.getBean(SimpleMessageListenerContainer.class);
        container.removeQueueNames(queueNmae);
        return "delete "+queueNmae+" ok";
    }

}
