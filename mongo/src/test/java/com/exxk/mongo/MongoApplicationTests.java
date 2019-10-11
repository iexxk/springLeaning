package com.exxk.mongo;

import com.mongodb.BasicDBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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
    public void addBaseUser(){
        BaseUser user=new BaseUser();
        user.setAge(18);
        user.setName("Lisa");
        mongoTemplate.save(user);
    }

    @Test
    public void addUser(){
        User user=new User();
        BaseUser baseUser=new BaseUser();
        baseUser.setAge(18);
        baseUser.setName("Lisa");
        Map<String,BaseUser> baseUserMap=new HashMap<>();
        baseUserMap.put(baseUser.getName(),baseUser);
        user.setUserMap(baseUserMap);
        user.setNum("1");
        mongoTemplate.save(user);

    }
    @Test
    public void selectUser(){
        User user=new User();
        BaseUser baseUser=new BaseUser();
        baseUser.setAge(18);
        baseUser.setName("Lisa");
        Map<String,BaseUser> baseUserMap=new HashMap<>();
        baseUserMap.put(baseUser.getName(),baseUser);
        user.setUserMap(baseUserMap);
        user.setNum("1");
        Query query= new Query();
//        query.addCriteria(new Criteria().andOperator(Criteria.where("num").is("1")
//                ,Criteria.where("user_list.name").ne("lisa")));
        Criteria criteria=new Criteria();
        criteria.and("num").is("1");
        criteria.and("user_list.name").ne("lisa");
        query.addCriteria(criteria);
//        query.addCriteria(Criteria.where("num").is("1"));
//        query.addCriteria(Criteria.where("user_list.name").ne("lisa"));

        Update update=new Update();


        BaseUser baseUser2=new BaseUser();
        baseUser2.setName("lisa");
        baseUser2.setAge(20);
        update.addToSet("user_list",baseUser2);
        mongoTemplate.upsert(query,update,User.class);
    }


    @Test
    public void ss(){
        BasicDBObject bson = new BasicDBObject();
        bson.put("$eval","db.getCollection('user_info').update({'num':'1','user_list.name':{$ne:'kk'}},{$addToSet:{'user_list':{'name':'kk','age':1}}})");
        mongoTemplate.getDb().runCommand(bson);
    }


    @Test
    public void testList(){
        User user=new User();

        BaseUser baseUser=new BaseUser();
        baseUser.setAge(18);
        baseUser.setName("lisa");
        List<BaseUser> baseUserList=new ArrayList<>();
        baseUserList.add(baseUser);
        user.setNum("1");
        user.setUserList(baseUserList);

        mongoTemplate.save(user);
    }

    @Test
    public void update(){
        Query query= new Query();
//        query.addCriteria(new Criteria().andOperator(Criteria.where("num").is("1")
//                ,Criteria.where("user_list.name").ne("lisa")));
        Criteria criteria=new Criteria();
        criteria.and("num").is("1");
        criteria.and("dd").is("ss");
        query.addCriteria(criteria);

        Update update=new Update();


        BaseUser baseUser2=new BaseUser();
        baseUser2.setName("lisa");
        baseUser2.setAge(20);
        update.addToSet("user_list",baseUser2);
        mongoTemplate.upsert(query,update,User.class);

    }

    @Test
    public void addUser2(){
//        User user=new User();
//        BaseUser baseUser=new BaseUser();
//        baseUser.setAge(18);
//        baseUser.setName("Lisa1");
//        List<BaseUser> baseUsers=new ArrayList<>();
//        baseUsers.add(baseUser);
//        user.setUsers(baseUsers);
//        user.setNum("1");
//
//        Query query=new Query(Criteria.where("num").is(1));
//        Update update = Update.update("users",baseUsers);
//       // mongoTemplate.updateFirst(query, update, User.class);
//        mongoTemplate.upsert(query, new Update().set("cname", "zcy"), User.class);

    }

//    @Test
//    public void selectUser() throws InterruptedException {
//        List<User> userList= mongoTemplate.findAll(User.class);
//        System.out.println("用户数量："+userList.size());
//
//
//        ChangeStreamOptions options= ChangeStreamOptions.builder().filter().build();
//        Flux<ChangeStreamEvent<User>> flux = reactiveMongoTemplate.changeStream("user", options, User.class);
//
//        flux.subscribe(userChangeStreamEvent -> {
//            userChangeStreamEvent.getBody();
//        });
//        TimeUnit.MINUTES.sleep(5);
//
//    }

    @Test
    public void addDbrefUser(){
        DbrefUser dbrefUser=new DbrefUser();
        dbrefUser.setAddress("dasd");
        BaseUser baseUser=new BaseUser();
        baseUser.setAge(12);
        baseUser.setName("wangwu");
        baseUser.setId("234324");
        dbrefUser.setBaseUser(baseUser);
        mongoTemplate.save(dbrefUser);

    }

}
