/**
 * User Service
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper user;

    public Map<String,Object> allId(String name){
        return user.allId(name);
    }

    public List<User> allUser(){
        return user.allUser();
    }

    public Map<String,Object> mapUser(String username){
        return user.mapUser(username);
    }

}
