/**
 * User Service
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.enums.UserTypeEnum;
import com.minipgm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Login
     *
     * @param userId   User's Id
     * @param password User's password
     * @return Operation status code
     */

    public int login(int userId, String password) {
        if (userMapper.isActivated(userId, "activated") != null) {
            String encryptedPassword = shaEncryption.passwordEncryption(password);
            if (userMapper.existUser(userId, encryptedPassword, UserTypeEnum.ADMIN) != null)
                return operationStatus.SUCCESSFUL;
            else
                return operationStatus.FAILED;
        } else {
            return operationStatus.INACTIVATED;
        }

    }

    /**
     * Activate account
     *
     * @param userId       User's Id
     * @param password     User's password
     * @param activateCode A code to activate the account which assign by system
     * @return Operation status code
     */

    public int activateAccount(int userId, String password, int activateCode) {
        String encryptedPassword = shaEncryption.passwordEncryption(password);
        return userMapper.activateAccount(userId, encryptedPassword, activateCode);
    }

    public User onlineUser(int userId) {
        return userMapper.getUserById(userId);
    }

    public int createAccount(){

        return 0;
    }


}
