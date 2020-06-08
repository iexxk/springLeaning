package com.exxk.testdemo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TestService {
    /**
     * 异步任务
     * @param s
     * @return
     */
    @Async
    public Future<JSONObject> asyncTask(int s, String name){
        JSONObject jsonObject=new JSONObject();
        try {
            Thread.sleep(1000*s);
            jsonObject.put(name,s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(jsonObject);
    }


}
