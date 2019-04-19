package com.minipgm.security.monitor;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MonitorMapper {

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
    @Select("SELECT * FROM security WHERE process_status=0 AND process_status=1")
    List<SecurityDTO> getActivatedSecurityList();

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
            "WHERE process_status=2 AND process_status=3 " +
            "LIMIT #{low},#{high}")
    List<SecurityDTO> getFinishedSecurityList(int low,int high);

}
