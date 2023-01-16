package com.example.jsonstudy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

@Configuration
public class WebMvcConfig {

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter  converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        converter.setObjectMapper(objectMapper);
        return converter;
    }
    /**
     * https://blog.csdn.net/java_miss_you/article/details/104326415
     * 这篇blog说可以直接采用下面这种写法
     */
    // @Bean
    // ObjectMapper objectMapper(){
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    //     return objectMapper;
    // }
}