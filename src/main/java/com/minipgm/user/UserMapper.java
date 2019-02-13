/**
 * User Mapper
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;


import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface UserMapper {

    //search test
    @Select("SELECT id as uniqueID FROM userinfo where name=#{name}")
    Map<String, Object> allId(String name);

    @Select("SELECT * FROM user_auth")
    List<User> allUser();

    @Select("SELECT * FROM user_auth where username=#{username}")
    Map<String, Object> mapUser(String username);
    //test end

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND password=#{password}")
    String existUser(int userId, String password);


}
