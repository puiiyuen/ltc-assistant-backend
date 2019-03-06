/**
 * Resident Mapper
 *
 * @author Peiyuan
 * 2019-002-24
 */
package com.minipgm.resident;

import com.minipgm.enums.SexEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResidentMapper {

    @Results({
            @Result(property = "resId", column = "res_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "goverId", column = "gover_id"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "address", column = "address"),
            @Result(property = "photoUrl", column = "photo_url"),
            @Result(property = "egName", column = "emergency_name"),
            @Result(property = "egPhone", column = "emergency_phone"),
            @Result(property = "familyId", column = "family_member_id"),
            @Result(property = "moveInDate", column = "move_in_date"),
            @Result(property = "moveOutDate", column = "move_out_date"),
            @Result(property = "medicalHistory", column = "medical_history"),
            @Result(property = "numOfBed", column = "num_bed"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email")

    })
    @Select("SELECT res.*, user.phone, user.email FROM res_profile AS res,user_auth AS user " +
            "WHERE user.user_id=res.res_id LIMIT #{low},#{high}")
    List<ResidentDetail> getRangeResidents(int low, int high);

    @Results({
            @Result(property = "resId", column = "res_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "goverId", column = "gover_id"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "address", column = "address"),
            @Result(property = "photoUrl", column = "photo_url"),
            @Result(property = "egName", column = "emergency_name"),
            @Result(property = "egPhone", column = "emergency_phone"),
            @Result(property = "familyId", column = "family_member_id"),
            @Result(property = "moveInDate", column = "move_in_date"),
            @Result(property = "moveOutDate", column = "move_out_date"),
            @Result(property = "medicalHistory", column = "medical_history"),
            @Result(property = "numOfBed", column = "num_bed"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email")

    })
    @Select("SELECT res.*, user.phone, user.email FROM res_profile AS res,user_auth AS user " +
            "WHERE user.user_id=res.res_id AND user.user_id=#{userId}")
    ResidentDetail getResidentById(int userId);

    @Select("SELECT name FROM res_profile WHERE gover_id=#{goverId}")
    String existResident(String goverId);

    @Insert("INSERT INTO res_profile (res_id,name,sex,dob,num_bed,gover_id,address," +
            "emergency_name,emergency_phone,family_member_id,move_in_date,medical_history) " +
            "VALUES (#{resId},#{name},#{sex},#{dob},#{numOfBed},#{goverId},#{address}," +
            "#{egName},#{egPhone},#{famId},#{moveInDate},#{medicalHistory})")
    int createResident(int resId, String name, SexEnum sex, String dob,
                       int numOfBed, String goverId, String address,String egName,
                       String egPhone,int famId,String moveInDate,String medicalHistory);

    @Insert("INSERT INTO user_auth (user_id,username,user_type,regcode) VALUES (#{userId},#{username},#{userType},#{regcode})")
    int createResidentFamily();

}
