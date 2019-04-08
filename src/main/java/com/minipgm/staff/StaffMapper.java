/**
 * Staff Mapper
 *
 * @author Peiyuan
 * 2019-04-08
 */

package com.minipgm.staff;

import com.minipgm.enums.SexEnum;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface StaffMapper {

    @Results({
            @Result(property = "staffId", column = "staff_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "status",column = "current_status"),
    })
    @Select("SELECT s.staff_id,s.name,s.sex,s.dob,s.current_status,u.phone " +
            "FROM staff_profile as s,user_auth as u " +
            "WHERE u.user_id=s.staff_id")
    List<StaffToList> getStaffList();

    @Results({
            @Result(property = "staffId", column = "staff_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "status",column = "current_status"),
    })
    @Select("SELECT s.*,u.phone,u.email " +
            "FROM staff_profile as s,user_auth as u " +
            "WHERE u.user_id=s.staff_id AND u.user_id=#{staffId}")
    Staff getStaffDetail(int staffId);

    @Select("SELECT name FROM staff_profile WHERE gover_id=#{goverId}")
    String existStaff(String goverId);

    @Insert("INSERT INTO staff_profile (staff_id,name,sex,dob,gover_id,address,photo_url,move_in_date) " +
            "VALUES (#{staffId},#{name},#{sex},#{dob},#{goverId},#{address},#{photoUrl},#{moveInDate})")
    int addStaff(int staffId, String name, SexEnum sex, Date dob,
                 String goverId,String address,String photoUrl,Date moveInDate);

    @Update("UPDATE staff_profile SET name=#{name},sex=#{sex},dob=#{dob},gover_id=#{goverId},address=#{address}," +
            "photo_url=#{photoUrl},move_in_date=#{moveInDate},update_date=CURRENT_TIMESTAMP " +
            "WHERE staff_id=#{staffId}")
    int modifyStaff(int staffId,String name,SexEnum sex,Date dob,String goverId,String address,
                    String photoUrl,Date moveInDate);

    @Delete("DELETE FROM staff_profile WHERE staff_id=#{staffId}")
    int deleteStaff(int staffId);


}
