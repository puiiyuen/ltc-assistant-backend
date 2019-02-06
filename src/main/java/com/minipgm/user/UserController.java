
/**
 * User Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/register")
    public boolean register(){
        return true;
    }

    @RequestMapping("/allid")
    public Map<String,Object> allId(String name) {
        return service.allId(name);
    }

    @RequestMapping("/alluser")
    public List<User> allUser() {
        return service.allUser();
    }

    @RequestMapping("/mapuser")
    public Map<String,Object> mapUser(String username){
        return service.mapUser(username);
    }


}
