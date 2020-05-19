package com.exxk.testdemo;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        JSONObject oldObj=new JSONObject();
        oldObj.put("a","1234");
        oldObj.put("b",1234);
        oldObj.put("c",123);
        oldObj.put("d",1234);

        JSONObject newObj=new JSONObject();
        newObj.put("a","123");
        newObj.put("b",123);
        newObj.put("c",123);
        newObj.put("e",oldObj);



       JSONObject change= JsonUtil.change(oldObj,newObj);
       log.info(change.toJSONString());
    }
}
