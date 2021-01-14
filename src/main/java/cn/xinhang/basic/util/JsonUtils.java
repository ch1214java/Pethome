package cn.xinhang.basic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/14 14:11
 */
public class JsonUtils {

    /**
     * 将对象转化为json字符串
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 将json字符串转化为指定类型的对象
     * @param jsonStr
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> clz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonStr, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
