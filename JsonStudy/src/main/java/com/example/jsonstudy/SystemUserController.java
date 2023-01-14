package com.example.jsonstudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
// @Controller
@RequestMapping("/jsonFormat1")
public class SystemUserController {
 
    @GetMapping("/user")
    public SystemUser getUser() {
        return new SystemUser("1", "小明", "xiaoming123");
    }
 
    @GetMapping("/list")
    public List<SystemUser> getUserList() {
        List<SystemUser> list= new ArrayList<>();
        SystemUser xiaoming= new SystemUser("1", "小明", "xiaoming123");
        SystemUser xiaoli = new SystemUser("2", "小丽", "xiaoli123");
        list.add(xiaoming);
        list.add(xiaoli);
        list.add(new SystemUser("3","nanke","dot",new Date()));
        return list;
    }
 
    @GetMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        SystemUser user = new SystemUser("1", "xiaoming", "xiaomign123");
        map.put("用户信息", null);
        // {"用户信息":null,"备注说明":"hello world","在干啥呢":"working","一串数字":123456789}
        // 需要处理null
        map.put("在干啥呢", "working");
        map.put("备注说明", "hello world");
        map.put("一串数字",123456789);
        return map;
    }

}