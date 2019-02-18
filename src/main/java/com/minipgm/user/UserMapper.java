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

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND password=#{password}")
    String existUser(int userId, String password);

    @Select("SELECT * FROM user_auth WHERE user_id=#{userId}")
    User getUserById(int userId);

    @Update("UPDATE user_auth SET password=#{password},account_status='activated' WHERE user_id=#{userId} AND regcode=#{activateCode}")
    int activateAccount(int userId, String password, int activateCode);


}
