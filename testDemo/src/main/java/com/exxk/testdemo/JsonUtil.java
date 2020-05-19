package com.exxk.testdemo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 返回newObj在oldObj基础上发生了什么变化，返回变化的值
     * oldObj 不能为null，如果要设置null，以""字符串为准
     * @param oldObj
     * @param newObj
     * @return
     */
    public static JSONObject change(JSONObject oldObj,JSONObject newObj){
        JSONObject change;
        change= JSON.parseObject(newObj.toJSONString());
        Iterator<Map.Entry<String, Object>> entryIterator = change.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String, Object> entry = entryIterator.next();
            String key= entry.getKey();
            if (oldObj.containsKey(key)){
                if (oldObj.get(key).equals(change.get(key))){
                    entryIterator.remove();
                }
            }
        }
        return change;
    }

}
