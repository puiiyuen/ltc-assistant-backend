/**
 * Location Mapper
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.security.location;

import com.minipgm.security.location.utils.Point;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface LocationMapper {

    @Insert("INSERT INTO location (user_id,longitude,latitude,unix_ts) " +
            "VALUES(#{userId},#{longitude},#{latitude},#{unixTs})")
    int addUserLocation(int userId, double longitude, double latitude, String unixTs);

    @Results({
            @Result(property = "userId",column = "user_id"),
            @Result(property = "longitude", column = "longitude"),
            @Result(property = "latitude", column = "latitude"),
            @Result(property = "unixTs",column = "unix_ts"),
            @Result(property = "recordDate", column = "record_date")
    })
    @Select("SELECT * FROM location loc " +
            "WHERE unix_ts=(SELECT max(unix_ts) FROM location WHERE loc.user_id = user_id)")
    List<Location> getUserLocation();

    @Results({
            @Result(property = "x", column = "longitude"),
            @Result(property = "y", column = "latitude")
    })
    @Select("SELECT longitude,latitude FROM geofence")
    List<Point> getFence();

    @Insert("INSERT INTO geofence (user_id,gid,longitude,latitude) " +
            "VALUES(#{userId},#{gid},#{longitude},#{latitude})")
    int addFencePoint(int userId, String gid, double longitude, double latitude);

    @Delete("DELETE FROM geofence WHERE gid=#{gid}")
    int deleteFencePoint(String gid);

    @Insert("INSERT INTO security (incident_id,res_id,type,longitude,latitude,record_time) " +
            "SELECT #{incidentId},#{resId},#{type},#{longitude},#{latitude},#{recordDate} " +
            "FROM dual WHERE NOT EXISTS (SELECT res_id FROM security " +
            "WHERE (res_id = #{resId} AND type = #{type} AND (process_status=0 OR process_status=1)) " +
            "OR (res_id = #{resId} AND type = #{type} AND record_time=#{recordDate})) ")
    int addSecurityRecord(String incidentId, int resId, int type,
                          double longitude, double latitude, Timestamp recordDate);

    @Select("SELECT MAX(incident_id) FROM security WHERE incident_id LIKE #{todayId}")
    String maxIncidentId(String todayId);

}
