/**
 * Resident Mapper
 *
 * @author Peiyuan
 * 2019-002-24
 */
package com.minipgm.resident;

import com.minipgm.enums.SexEnum;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ResidentMapper {

    @Results({
            @Result(property = "resId", column = "res_id"),
            @Result(property = "name", column = "res_name"),
            @Result(property = "goverId", column = "gover_id"),
            @Result(property = "sex", column = "res_sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "numOfBed", column = "num_bed"),
            @Result(property = "phone", column = "res_phone"),
            @Result(property = "email", column = "res_email"),
            @Result(property = "address", column = "res_address"),
            @Result(property = "photoUrl", column = "photo_url"),
            @Result(property = "moveInDate", column = "move_in_date"),
            @Result(property = "moveOutDate", column = "move_out_date"),
            @Result(property = "medicalHistory", column = "medical_history"),
            @Result(property = "familyId", column = "fam_id"),
            @Result(property = "famName", column = "fam_name"),
            @Result(property = "famPhone", column = "fam_phone"),
            @Result(property = "famAddress", column = "fam_address"),
            @Result(property = "famEmail", column = "fam_email"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT res.res_id,res.name as res_name,res.sex as res_sex,res.dob,res.num_bed,res.gover_id," +
            "res.address as res_address,res.photo_url,res.move_in_date,res.move_out_date,res.medical_history," +
            "res.create_date,res.update_date,user.phone  as res_phone,user.email  as res_email,fam.* " +
            "FROM res_profile AS res,user_auth AS user, " +
            "(SELECT fam.fam_id,fam.fam_name,fam.fam_address,user.phone AS fam_phone,user.email AS fam_email " +
            "FROM fam_profile AS fam,user_auth AS user WHERE fam.fam_id = user.user_id) AS fam " +
            "WHERE user.user_id = res.res_id AND fam.fam_id = res.family_member_id AND account_status!='destroy'" +
            "LIMIT #{low},#{high}")
    List<Resident> getRangeResidents(int low, int high);

    @Results({
            @Result(property = "resId", column = "res_id"),
            @Result(property = "name", column = "res_name"),
            @Result(property = "goverId", column = "gover_id"),
            @Result(property = "sex", column = "res_sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "numOfBed", column = "num_bed"),
            @Result(property = "phone", column = "res_phone"),
            @Result(property = "email", column = "res_email"),
            @Result(property = "address", column = "res_address"),
            @Result(property = "photoUrl", column = "photo_url"),
            @Result(property = "moveInDate", column = "move_in_date"),
            @Result(property = "moveOutDate", column = "move_out_date"),
            @Result(property = "medicalHistory", column = "medical_history"),
            @Result(property = "familyId", column = "fam_id"),
            @Result(property = "famName", column = "fam_name"),
            @Result(property = "famPhone", column = "fam_phone"),
            @Result(property = "famAddress", column = "fam_address"),
            @Result(property = "famEmail", column = "fam_email"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT res.res_id,res.name as res_name,res.sex as res_sex,res.dob,res.num_bed,res.gover_id," +
            "res.address as res_address,res.photo_url,res.move_in_date,res.move_out_date,res.medical_history," +
            "res.create_date,res.update_date,user.phone  as res_phone,user.email  as res_email,fam.* " +
            "FROM res_profile AS res,user_auth AS user, " +
            "(SELECT fam.fam_id,fam.fam_name,fam.fam_address,user.phone AS fam_phone,user.email AS fam_email " +
            "FROM fam_profile AS fam,user_auth AS user WHERE fam.fam_id = user.user_id) AS fam " +
            "WHERE user.user_id = res.res_id AND fam.fam_id = res.family_member_id AND account_status!='destroy' AND " +
            "(res.res_id = #{resId} OR res.name = #{name} OR res.num_bed = #{numOfBed})")
    List<Resident> searchResident(int resId, String name, int numOfBed);

    @Results({
            @Result(property = "resId", column = "res_id"),
            @Result(property = "name", column = "res_name"),
            @Result(property = "goverId", column = "gover_id"),
            @Result(property = "sex", column = "res_sex"),
            @Result(property = "dob", column = "dob"),
            @Result(property = "numOfBed", column = "num_bed"),
            @Result(property = "phone", column = "res_phone"),
            @Result(property = "email", column = "res_email"),
            @Result(property = "address", column = "res_address"),
            @Result(property = "photoUrl", column = "photo_url"),
            @Result(property = "moveInDate", column = "move_in_date"),
            @Result(property = "moveOutDate", column = "move_out_date"),
            @Result(property = "medicalHistory", column = "medical_history"),
            @Result(property = "familyId", column = "fam_id"),
            @Result(property = "famName", column = "fam_name"),
            @Result(property = "famPhone", column = "fam_phone"),
            @Result(property = "famAddress", column = "fam_address"),
            @Result(property = "famEmail", column = "fam_email"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    @Select("SELECT res.res_id,res.name as res_name,res.sex as res_sex,res.dob,res.num_bed,res.gover_id," +
            "res.address as res_address,res.photo_url,res.move_in_date,res.move_out_date,res.medical_history," +
            "res.create_date,res.update_date,user.phone  as res_phone,user.email  as res_email,fam.* " +
            "FROM res_profile AS res,user_auth AS user, " +
            "(SELECT fam.fam_id,fam.fam_name,fam.fam_address,user.phone AS fam_phone,user.email AS fam_email " +
            "FROM fam_profile AS fam,user_auth AS user WHERE fam.fam_id = user.user_id) AS fam " +
            "WHERE user.user_id = res.res_id AND fam.fam_id = res.family_member_id " +
            "AND user.user_id=#{userId} AND account_status!='destroy'")
    Resident getResidentById(int userId);

    @Select("SELECT name FROM res_profile WHERE gover_id=#{goverId}")
    String existResident(String goverId);

    @Insert("INSERT INTO res_profile (res_id,name,sex,dob,num_bed,gover_id,address," +
            "family_member_id,move_in_date,medical_history) " +
            "VALUES (#{resId},#{name},#{sex},#{dob},#{numOfBed},#{goverId},#{address}," +
            "#{famId},#{moveInDate},#{medicalHistory})")
    int createResident(int resId, String name, SexEnum sex, Date dob,
                       int numOfBed, String goverId, String address, int famId,
                       Date moveInDate, String medicalHistory);

    @Update("UPDATE res_profile SET photo_url=#{photoUrl},update_date=CURRENT_TIMESTAMP" +
            " WHERE gover_id=#{goverId}")
    int updatePhotoByGoverId(String photoUrl, String goverId);

    @Insert("INSERT INTO fam_profile (fam_id,fam_name,fam_address) VALUES (#{famId},#{famName},#{famAddress})")
    int createResidentFamily(int famId, String famName, String famAddress);

    @Update("UPDATE res_profile SET name=#{name},sex=#{sex},dob=#{dob},num_bed=#{numOfBed}," +
            "gover_id=#{goverId},address=#{address},family_member_id=#{famId},move_in_date=#{moveInDate}," +
            "medical_history=#{medicalHistory},update_date=CURRENT_TIMESTAMP WHERE res_id=#{resId}")
    int modifyResident(int resId, String name, SexEnum sex, Date dob,
                       int numOfBed, String goverId, String address, int famId,
                       Date moveInDate, String medicalHistory);

    @Update("UPDATE fam_profile SET fam_name=#{famName},fam_address=#{famAddress},update_date=CURRENT_TIMESTAMP " +
            "WHERE fam_id=#{famId}")
    int modifyResidentFamily(int famId,String famName,String famAddress);

    @Delete("DELETE FROM res_profile WHERE res_id=#{resId}")
    int deleteResident(int resId);

    @Delete("DELETE FROM fam_profile WHERE fam_id=#{famId}")
    int deleteResidentFamily(int famId);


}
