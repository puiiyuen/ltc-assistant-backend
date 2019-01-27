package com.minipgm.user;


import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //search
    @Select("SELECT id FROM userinfo where username=#{name}")
    List<Integer> allId(String name);

    @Select("SELECT * FROM userinfo")
    List<User> allUser();

}
