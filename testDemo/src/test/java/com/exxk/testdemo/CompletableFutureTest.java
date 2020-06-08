package com.exxk.testdemo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompletableFutureTest {

    private static final Logger log = LoggerFactory.getLogger(CompletableFutureTest.class);

    public static void main(String[] args) throws IOException, InterruptedException {

    }

    @Autowired
    TestService testService;

    @Test
    public void thenCombine() throws Exception {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
           delaySec(10);
           log.info("第一个CF");
            return "hello1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            log.info("第二个CF");
            delaySec(13);
            return "hello2";
        }), (t, u) -> {
            log.info("1+2="+t+u);
            return t + " " + u;
        });
        log.info("bbbb");
        log.info(result.get());
        log.info("aaaa");

    }

    @Test
    public void thenFuture(){
        log.info("start time");
        Future<JSONObject> future1 = testService.asyncTask(10,"task1");
        Future<JSONObject> future2 = testService.asyncTask(20,"task2");
        try {
            JSONObject resObj = future1.get();
            JSONObject resObj2 = future2.get();
            for (String key : resObj2.keySet()) {
                resObj.put(key, resObj2.get(key));
            }
            log.info(resObj.toJSONString());
            log.info("end time");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public void delaySec(int s){
        try {
            Thread.sleep(1000*s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
