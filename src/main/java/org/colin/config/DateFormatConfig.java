package org.colin.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.colin.utils.Tool;
import org.springframework.boot.jackson.JsonComponent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Description: 全局日期格式化
 * @ClassName: DateFormatConfig
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/20 10:15
 * @Version: 1.1.0
 */
@JsonComponent
@Slf4j
public class DateFormatConfig {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期格式化
     */
    public static class DateJsonSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(dateFormat.format(date));
        }
    }

    /**
     * 解析日期字符串
     */
    public static class DateJsonDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            try {
                if(!Tool.isBlank(jsonParser.getText())){
                    return dateFormat.parse(jsonParser.getText());
                }
                return null;
            } catch (ParseException e) {
                log.error("解析日期字符串-发生异常:{}", e);
                throw new RuntimeException(e);
            }
        }
    }
}
