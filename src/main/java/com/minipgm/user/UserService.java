package com.minipgm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper user;

    public List<Integer> allId(String name){
        return user.allId(name);
    }

    public List<User> allUser(){
        return user.allUser();
    }

}
