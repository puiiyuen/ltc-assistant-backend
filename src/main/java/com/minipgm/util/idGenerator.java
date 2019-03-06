package com.minipgm.util;

import com.minipgm.user.UserMapper;

import java.util.Calendar;

public class idGenerator {

    private static UserMapper userMapper;

    private static int today() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        year = year % 2000 * 10000;
        month *= 100;

        return year + month + day;

    }

    public static int newId(int type) {
        int no = userMapper.countAccountByDay(todayId(type));
        if (no > 999) {//daily max 1000
            return -1;
        } else {
            return today() * 10000 + type * 1000 + no;
        }
    }

    private static int todayId(int type) {
        return today() * 10 + type;
    }
}
