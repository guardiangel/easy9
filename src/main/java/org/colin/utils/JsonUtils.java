package org.colin.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: json序列化工具类
 * @ClassName: JsonUtils
 * @Author: wujiangbo
 * @Date: 2020/7/8 0008 16:41
 * @Version: 1.1.0
 */
public class JsonUtils {

    /**
     * 定义jackson对象
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 忽略JSON中没有的字段，防止反序列化对象的时候报找不到属性字段的异常
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 如果json字符串中含有新行时，加上这个
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 空值转换异常
        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /*
     * @Description: 将对象转换成json字符串
     * @MethodName: objectToJson
     * @param:  [data]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/7/8 0008 16:42
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String objectToJsonWithType(Object data, TypeReference typeReference){
        try {
            return MAPPER.writerFor(typeReference).writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @Description: 将json结果集转化为对象
     * @MethodName: jsonToPojo
     * @param:  [jsonData, beanType]
     * @return: T
     * @Author: wujiangbo
     * @Date: 2020/7/8 0008 16:43
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * @Description: 将json数据转换成pojo对象list
     * @MethodName: jsonToList
     * @param:  [jsonData, beanType]
     * @return: java.util.List<T>
     * @Author: wujiangbo
     * @Date: 2020/7/8 0008 16:43
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @Description: json字符串转Map
     * @MethodName: parseMap
     * @param:  [jsonStr]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: wujiangbo
     * @Date: 2020/7/8 0008 16:43
     */
    public static Map<String, Object> parseMap(String jsonStr) throws IOException {
        return MAPPER.readValue(jsonStr, Map.class);
    }

    public static List<String> parseList(String jsonStr) throws IOException {
        return MAPPER.readValue(jsonStr, new TypeReference<List<String>>() {});
    }
}
