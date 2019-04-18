
/**
 * User Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.enums.UserTypeEnum;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            int userId = Integer.parseInt(param.get("userId").toString());
            String password = param.get("password").toString();
            Map<String, Object> toLogin = userService.login(userId, password);
            User user  = (User) toLogin.get("user");
            if (Integer.parseInt(toLogin.get("status").toString()) == operationStatus.SUCCESSFUL) {
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userType", user.getUserType());
                if (user.getUserType() == UserTypeEnum.ADMIN){
                    session.setMaxInactiveInterval(600);
                }
                if (user.getUserType() == UserTypeEnum.RESIDENT
                        || user.getUserType() == UserTypeEnum.RESFAMILY){
                    session.setMaxInactiveInterval(7*24*3600);
                }
                return operationStatus.SUCCESSFUL;
            } else {
                return Integer.parseInt(toLogin.get("status").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/activate")
    public int activate(@RequestBody Map<String, Object> param) {
        try {
            int userId = Integer.parseInt(param.get("userId").toString());
            String password = param.get("password").toString();
            int activateCode = Integer.parseInt(param.get("activateCode").toString());
            if (userService.activateAccount(userId, password, activateCode) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @GetMapping("/session")
    public int sessionCheck(HttpSession session) {
        try {
            if (session.getAttribute("userId") != null) {
                return operationStatus.IS_EXIST;
            } else {
                return operationStatus.NOT_EXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @GetMapping("/user")
    public User getOnlineUser(HttpSession session) {
        User fetch = new User();
        try {
            if (session.getAttribute("userId") != null) {
                fetch = userService.onlineUser(Integer.parseInt(session.getAttribute("userId").toString()));
                return fetch;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fetch.defaultUser();
    }

    @GetMapping("/logout")
    public int logout(HttpSession session) {
        try {
            session.invalidate();
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
}
