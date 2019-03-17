package com.minipgm.util;

import javax.servlet.http.HttpSession;

public class sessionCheck {
    public static boolean isOnline(HttpSession session, String userType){
        return session.getAttribute("userId") != null &&
                session.getAttribute("userType").toString().equals(userType);
    }
}
