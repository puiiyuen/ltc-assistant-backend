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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
            if (userMapper.existUser(userId, encryptedPassword, UserTypeEnum.ADMIN) != null) {
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }

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

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int activateAccount(int userId, String password, int activateCode) {
        try {
            String encryptedPassword = shaEncryption.passwordEncryption(password);
            return userMapper.activateAccount(userId, encryptedPassword, activateCode);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    public User onlineUser(int userId) {
        return userMapper.getUserById(userId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int createAccount(int userId, String username, UserTypeEnum userType,
                             int regcode, String phone, String email) {
        try {
            userMapper.createAccount(userId, username, userType, regcode, phone, email);
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }

    }
}
