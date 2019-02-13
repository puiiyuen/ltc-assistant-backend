
/**
 * User Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    //test demo
    @RequestMapping("/allid")
    public Map<String, Object> allId(String name) {
        return service.allId(name);
    }

    @RequestMapping("/alluser")
    public List<User> allUser() {
        return service.allUser();
    }

    @RequestMapping("/mapuser")
    public Map<String, Object> mapUser(String username) {
        return service.mapUser(username);
    }
    //end test demo

    @PostMapping(value = "/login")
    public int login(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(param.get("userId").toString());
        String password = param.get("password").toString();
        int operation = (service.login(userId, password));
        if (operation == operationStatus.SUCCESSFUL)
            return operationStatus.SUCCESSFUL;
        else
            return operationStatus.FAILED;

    }


}
