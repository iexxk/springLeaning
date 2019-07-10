package com.exxk.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addUser(){
        User user=new User();
        user.setAge(18);
        user.setName("Lisa");
        mongoTemplate.save(user);
    }

    @Test
    public void selectUser() throws InterruptedException {
        List<User> userList= mongoTemplate.findAll(User.class);
        System.out.println("用户数量："+userList.size());


        ChangeStreamOptions options= ChangeStreamOptions.builder().filter().build();
        Flux<ChangeStreamEvent<User>> flux = reactiveMongoTemplate.changeStream("user", options, User.class);

        flux.subscribe(userChangeStreamEvent -> {
            userChangeStreamEvent.getBody();
        });
        TimeUnit.MINUTES.sleep(5);

    }

}
