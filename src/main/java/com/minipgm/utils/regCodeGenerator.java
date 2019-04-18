package com.minipgm.utils;

import com.minipgm.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class regCodeGenerator {

    @Autowired
    private UserMapper userMapper;

    public int newRegCode() {

        if (userMapper.sumRegCode() > 49495500) {// sum of 1000-9999
            return -1;
        } else {
            int regcode;
            while(userMapper.existRegCode(regcode = 1000 + (int) (Math.random() * 9000))!=null);
            return regcode;
        }
    }
}
