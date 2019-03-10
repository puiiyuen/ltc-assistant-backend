/**
 * User Mapper
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.user;

import com.minipgm.enums.UserTypeEnum;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND account_status=#{accountStatus}")
    String isActivated(int userId, String accountStatus);

    @Select("SELECT username FROM user_auth WHERE user_id=#{userId} AND password=#{password} AND user_type=#{userType}")
    String existUser(int userId, String password, UserTypeEnum userType);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email")
    })
    @Select("SELECT user_id,username,phone,email FROM user_auth WHERE user_id=#{userId}")
    User getUserById(int userId);

    @Update("UPDATE user_auth SET password=#{password},account_status='activated',regcode=-1 " +
            "WHERE user_id=#{userId} AND regcode=#{activateCode}")
    int activateAccount(int userId, String password, int activateCode);

    @Insert("INSERT INTO user_auth (user_id,username,user_type,regcode,phone,email) " +
            "VALUES (#{userId},#{username},#{userType},#{regcode},#{phone},#{email})")
    int createAccount(int userId, String username,UserTypeEnum userType,int regcode,String phone,String email);

    @Select("SELECT COUNT(*) AS user_amount FROM user_auth WHERE user_id LIKE #{ids}")
    int countAccountByDay(String ids);

    @Select("SELECT username FROM user_auth WHERE regcode=#{regcode}")
    String existRegCode(int regcode);

    @Select("SELECT SUM(regcode) FROM user_auth WHERE regcode>=1000")
    int sumRegCode();


}
