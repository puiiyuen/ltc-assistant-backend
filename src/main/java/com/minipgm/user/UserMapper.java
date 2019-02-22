/**
 * User Mapper
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND account_status=#{accountStatus}")
    String isActivated(int userId, String accountStatus);

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND password=#{password} AND user_type='admin'")
    String existUser(int userId, String password);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email")
    })
    @Select("SELECT user_id,username,phone,email FROM user_auth WHERE user_id=#{userId}")
    User getUserById(int userId);

    @Update("UPDATE user_auth SET password=#{password},account_status='activated' WHERE user_id=#{userId} AND regcode=#{activateCode}")
    int activateAccount(int userId, String password, int activateCode);


}
