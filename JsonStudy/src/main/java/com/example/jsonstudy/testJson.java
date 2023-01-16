package com.example.jsonstudy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.List;

/**
 * @author zsl
 * @date 2023/1/14
 * @apiNote
 */
public class testJson {
    public static void main(String[] args) throws JsonProcessingException {
        Writer wanger = new Writer("沉默王二", 18);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(wanger);
        String s = mapper.writeValueAsString(wanger);
        System.out.println(s);

        String jsonString2 = "{\"name\":\"沉默王二\",\"age\":18}";
        Writer deserializedWriter = mapper.readValue(jsonString2, Writer.class);
        System.out.println(deserializedWriter);

        // 树模型
        JsonNode jsonNode = mapper.readTree(jsonString2);
        String name = jsonNode.get("name").asText();
        System.out.println(name);

        String json = "[{ \"name\" : \"沉默王三\", \"age\" : 18 }, { \"name\" : \"沉默王二\", \"age\" : 19 }]";
        List<Writer> writerList = mapper.readValue(json, new TypeReference<>() {
        });
        System.out.println(writerList);

        // 不要解析未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        String jsonStringAddUnknown = "{\"name\":\"沉默王二\",\"age\":18,\"sex\":\"男\"}";
        Writer deserializedWriter2 = mapper.readValue(jsonStringAddUnknown, Writer.class);
        System.out.println(deserializedWriter2);

        // 在序列化时忽略值为 null 的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略值为默认值的属性
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
    }
}
