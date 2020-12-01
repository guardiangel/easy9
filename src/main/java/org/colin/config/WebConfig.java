package org.colin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : 防止XSS攻击
 * @date : 2020-10-21 10:56:03
 */
@Configuration
public class WebConfig implements InitializingBean {

    /**
     * 默认就是@Autowired(required=true)，表示注入的时候，该bean必须存在，否则就会注入失败required = false,表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错
     */
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    private SimpleModule getSimpleModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new JsonHtmlXssDeserializer(String.class));
        return simpleModule;
    }

    /**
     * 初始化bean的时候执行，可以针对某个具体的bean进行配置。afterPropertiesSet 必须实现 InitializingBean接口。实现 InitializingBean接口必须实现afterPropertiesSet方法
     * 这个方法将在所有的属性被初始化后调用,但是会在init前调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (objectMapper != null) {
            SimpleModule simpleModule = getSimpleModule();
            objectMapper.registerModule(simpleModule);
        }
    }
}

/**
 * 对入参的json进行转义
 */
class JsonHtmlXssDeserializer extends JsonDeserializer<String> {

    public JsonHtmlXssDeserializer(Class<String> string) {
        super();
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        if (value != null) {
            return StringEscapeUtils.escapeHtml4(value.toString());
        }
        return null;
    }
}
