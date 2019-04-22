package com.minipgm.security.monitor;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MonitorMapper {

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "incidentId",column = "incident_id"),
            @Result(property = "resId",column = "resId"),
            @Result(property = "type",column = "type"),
            @Result(property = "longitude",column = "longitude"),
            @Result(property = "latitude",column = "latitude"),
            @Result(property = "recordTime",column = "record_time"),
            @Result(property = "staffId",column = "staff_id"),
            @Result(property = "processStatus",column = "process_status"),
            @Result(property = "processTime",column = "process_time"),
            @Result(property = "finishTime",column = "finish_time"),
            @Result(property = "comment",column = "comment")
    })
    @Select("SELECT r.name,s.* FROM security AS s,res_profile AS r " +
            "WHERE (process_status=0 OR process_status=1) AND r.res_id=s.res_id")
    List<SecurityDTO> getActivatedSecurityList();

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "incidentId",column = "incident_id"),
            @Result(property = "resId",column = "resId"),
            @Result(property = "type",column = "type"),
            @Result(property = "longitude",column = "longitude"),
            @Result(property = "latitude",column = "latitude"),
            @Result(property = "recordTime",column = "record_time"),
            @Result(property = "staffId",column = "staff_id"),
            @Result(property = "processStatus",column = "process_status"),
            @Result(property = "processTime",column = "process_time"),
            @Result(property = "finishTime",column = "finish_time"),
            @Result(property = "comment",column = "comment")
    })
    @Select("SELECT r.name,s.* FROM security AS s,res_profile AS r " +
            "WHERE (process_status=2 OR process_status=3) AND r.res_id=s.res_id " +
            "LIMIT #{low},#{high}")
    List<SecurityDTO> getFinishedSecurityList(int low,int high);

    @Results({
            @Result(property = "incidentId",column = "incident_id"),
            @Result(property = "resId",column = "resId"),
            @Result(property = "type",column = "type"),
            @Result(property = "longitude",column = "longitude"),
            @Result(property = "latitude",column = "latitude"),
            @Result(property = "recordTime",column = "record_time"),
            @Result(property = "staffId",column = "staff_id"),
            @Result(property = "processStatus",column = "process_status"),
            @Result(property = "processTime",column = "process_time"),
            @Result(property = "finishTime",column = "finish_time"),
            @Result(property = "comment",column = "comment")
    })
    @Select("SELECT * FROM security " +
            "WHERE process_status=0 AND incident_id>#{incidentId} ")
    List<SecurityDTO> getAlert(String incidentId);

    @Update("UPDATE staff_profile SET current_status=#{status} WHERE staff_id=#{staffId} ")
    int changeStaffStatus(int staffId,int status);

    @Update("UPDATE security SET process_status=1,process_time=CURRENT_TIMESTAMP,staff_id=#{staffId} " +
            "WHERE incident_id=#{incidentId}")
    int processIncident(String incidentId,int staffId);

    @Update("UPDATE security SET process_status=2,finish_time=CURRENT_TIMESTAMP,comment=#{comment} " +
            "WHERE incident_id=#{incidentId}")
    int finishIncident(String incidentId,String comment);

    @Update("UPDATE security SET process_status=3,finish_time=CURRENT_TIMESTAMP,process_time=CURRENT_TIMESTAMP," +
            "staff_id=#{staffId} WHERE incident_id=#{incidentId}")
    int ignoreIncident(String incidentId,int staffId);

}
