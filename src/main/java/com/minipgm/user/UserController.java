
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

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public int login(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            int userId = Integer.parseInt(param.get("userId").toString());
            String password = param.get("password").toString();
            int toLogin = service.login(userId, password);
            if (toLogin == operationStatus.SUCCESSFUL) {
                session.setAttribute("userId", userId);
                session.setMaxInactiveInterval(600);
                return operationStatus.SUCCESSFUL;
            } else {
                return toLogin;
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
            if (service.activateAccount(userId, password, activateCode) == 1) {
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
                return operationStatus.ISEXIST;
            } else {
                return operationStatus.NOTEXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
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
