package com.minipgm.util;

import com.minipgm.user.UserMapper;

public class regCodeGenerator {

    private static UserMapper userMapper;

    public static int newRegCode() {

        if (userMapper.sumRegCode() > 49495500) {// sum of 1000-9999
            return -1;
        } else {
            int regcode;
            while(userMapper.existRegCode(regcode = 1000 + (int) (Math.random() * 9000))!=null);
            return regcode;
        }
    }
}
