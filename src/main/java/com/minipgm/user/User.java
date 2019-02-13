/**
 * User Class
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.user.enums.*;

public class User {

    private int userId;
    private String username;
    private String phone;
    private String email;
    private String password;
    private UserTypeEnum userType;

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
