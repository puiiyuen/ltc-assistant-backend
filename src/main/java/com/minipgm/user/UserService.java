/**
 * User Service
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.enums.UserTypeEnum;
import com.minipgm.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String,Object> login(String userId, String password) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            if (userMapper.isExsit(userId)==null){
                result.put("status",operationStatus.FAILED);
                return result;
            }
            if (userMapper.isActivated(userId) != null) {
                String encryptedPassword = shaEncryption.passwordEncryption(password);
                User existUser = userMapper.existUser(userId, encryptedPassword);
                if (existUser != null) {
                    result.put("user",existUser);
                    result.put("status",operationStatus.SUCCESSFUL);
                } else {
                    result.put("status",operationStatus.FAILED);
                }
            } else {
                result.put("status",operationStatus.INACTIVATED);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status",operationStatus.SERVERERROR);
            return result;
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
    public int activateAccount(String userId, String password, int activateCode) {
        try {
            String encryptedPassword = shaEncryption.passwordEncryption(password);
            return userMapper.activateAccount(userId, encryptedPassword, activateCode);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    public User getUserById(int userId) {
        try {
            return userMapper.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int createAccount(int userId, String username, UserTypeEnum userType,
                             int regcode, String phone, String email) {
        try {
            userMapper.createAccount(userId, username, userType, regcode, phone, email);
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyContact(int userId, String phone, String email) {
        try {
            if (userMapper.modifyContact(userId, phone, email) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int destroyAccount(int userId) {
        try {
            if (userMapper.destroyAccount(userId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }
}
