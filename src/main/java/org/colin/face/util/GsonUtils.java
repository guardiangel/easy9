package org.colin.face.util;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

/**
 * @Description: JSON工具类
 * @ClassName: GsonUtils
 * @Author: wujiangbo
 * @Date: 2020/9/3 0003 13:29
 * @Version: 1.1.0
 */
public class GsonUtils {

    private static Gson gson = new GsonBuilder().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Type typeOfT) throws JsonParseException {
        return (T) gson.fromJson(json, typeOfT);
    }
}
