package com.minipgm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping
    public String helloUser(){
        return "Hello UserController";
    }

    @RequestMapping("/allid")
    public List<Integer> allId(String name){
        return service.allId(name);
    }

    @RequestMapping("/alluser")
    public List<User> allUser(){
        return service.allUser();
    }

}
