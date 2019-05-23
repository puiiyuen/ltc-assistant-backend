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

    @Select("SELECT username FROM user_auth " +
            "WHERE user_id=#{userId} OR phone=#{userId} OR email=#{userId}")
    String isExsit(String userId);

    @Select("SELECT username FROM user_auth " +
            "WHERE (user_id=#{userId} OR phone=#{userId} OR email=#{userId}) AND account_status='activated'")
    String isActivated(String userId);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userType", column = "user_type")
    })
    @Select("SELECT user_id,user_type FROM user_auth " +
            "WHERE (user_id=#{userId} OR phone=#{userId} OR email=#{userId}) AND password=#{password} " +
            "AND account_status='activated'")
    User existUser(String userId, String password);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "userType", column = "user_type")
    })
    @Select("SELECT user_id,username,phone,email,user_type FROM user_auth WHERE user_id=#{userId} " +
            "AND account_status='activated'")
    User getUserById(int userId);

    @Update("UPDATE user_auth SET password=#{password},account_status='activated',regcode=-1," +
            "update_date=CURRENT_TIMESTAMP " +
            "WHERE (user_id=#{userId} OR phone=#{userId} OR email=#{userId}) " +
            "AND regcode=#{activateCode} AND account_status='inactivated'")
    int activateAccount(String userId, String password, int activateCode);

    @Insert("INSERT INTO user_auth (user_id,username,user_type,regcode,phone,email) " +
            "VALUES (#{userId},#{username},#{userType},#{regcode},#{phone},#{email})")
    int createAccount(int userId, String username, UserTypeEnum userType, int regcode, String phone, String email);

    @Select("SELECT COUNT(*) AS user_amount FROM user_auth WHERE user_id LIKE #{ids}")
    int countAccountByDay(String ids);

    @Select("SELECT username FROM user_auth WHERE regcode=#{regcode}")
    String existRegCode(int regcode);

    @Select("SELECT SUM(regcode) FROM user_auth WHERE regcode>=1000")
    int sumRegCode();

    @Update("UPDATE user_auth SET phone=#{phone},email=#{email},update_date=CURRENT_TIMESTAMP" +
            " WHERE user_id=#{userId} AND account_status!='destroy'")
    int modifyContact(int userId, String phone, String email);

    @Update("UPDATE user_auth SET email=null,phone=null,account_status='destroy',update_date=CURRENT_TIMESTAMP " +
            "WHERE user_id=#{userId}")
    int destroyAccount(int userId);

}
