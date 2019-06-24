package com.exxk.websocketendpoint;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {

    /**
     * 校验json字符串
     * @param jsonStr
     * @return
     */
    public static boolean validate(String jsonStr) {
        JsonElement jsonElement;
        try {
            jsonElement = new JsonParser().parse(jsonStr);
        } catch (Exception e) {
            return false;
        }
        if (jsonElement == null) {
            return false;
        }
        if (!jsonElement.isJsonObject()) {
            return false;
        }
        return true;
    }

}
