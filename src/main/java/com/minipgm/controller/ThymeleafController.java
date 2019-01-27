package com.minipgm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping("/hello")
    public String toIndex(Map<String, Object> map){
        map.put("name","ph");
        return "thymeleaf/index";
    }
}
