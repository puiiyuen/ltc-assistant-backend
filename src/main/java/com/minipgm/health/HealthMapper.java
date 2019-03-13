/**
 * Health Mapper
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HealthMapper {

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "dob",column = "dob"),
            @Result(property = "numOfBed",column = "num_bed"),
            @Result(property = "resId",column = "res_id"),
            @Result(property = "recordDate",column = "record_date")
    })
    @Select("SELECT res.res_id,res.name,res.sex,res.dob,res.num_bed,rep.record_date FROM res_profile res LEFT OUTER JOIN " +
            "(SELECT * FROM health_report hea WHERE record_date=(SELECT MAX(record_date) " +
            "FROM health_report WHERE hea.res_id = res_id)) rep ON res.res_id=rep.res_id " +
            "LIMIT 0,20")
    List<HealthDTO> getHealthReportList();

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "dob",column = "dob"),
            @Result(property = "numOfBed",column = "num_bed"),
            @Result(property = "resId",column = "id"),
            @Result(property = "medicalHistory",column = "medical_history"),
            @Result(property = "height",column = "height"),
            @Result(property = "weight",column = "weight"),
            @Result(property = "heartRate",column = "heart_rate"),
            @Result(property = "bpSystolic",column = "bp_systolic"),
            @Result(property = "bpDiastolic",column = "bp_diastolic"),
            @Result(property = "bloodGlucose",column = "blood_glucose"),
            @Result(property = "bloodLipids",column = "blood_lipids"),
            @Result(property = "uricAcid",column = "uric_acid"),
            @Result(property = "suggestion",column = "suggestion"),
            @Result(property = "recordDate",column = "record_date")
    })
    @Select("SELECT res.res_id AS id,res.name,res.sex,res.dob,res.num_bed,res.medical_history,rep.* " +
            "FROM res_profile  res LEFT OUTER JOIN (SELECT * FROM health_report hea ) rep ON res.res_id=rep.res_id " +
            "WHERE res.res_id=#{resId} ORDER BY rep.record_date DESC ")
    List<HealthDTO> getReportById(int resId);



}
