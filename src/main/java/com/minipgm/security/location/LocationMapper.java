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
            @Result(property = "recordDate", column = "record_date")
    })
    @Select("SELECT user_id,longitude,longitude,max(record_date) AS record_date " +
            "FROM location GROUP BY user_id")
    List<Location> getUserLocation();

    @Results({
            @Result(property = "x", column = "longitude"),
            @Result(property = "y", column = "latitude")
    })
    @Select("SELECT longitude,latitude FROM geofence WHERE gid=#{gid}")
    List<Point> getFence(String gid);

    @Insert("INSERT INTO geofence (user_id,gid,longitude,latitude) " +
            "VALUES(#{userId},#{gid},#{longitude},#{latitude})")
    int addFencePoint(int userId, String gid, double longitude, double latitude);

    @Delete("DELETE FROM geofence WHERE gid=#{gid}")
    int deleteFencePoint(String gid);

    @Insert("INSERT INTO security (incident_id,res_id,type,longitude,latitude,record_date) " +
            "VALUES(#{incidentId},#{resId},#{type},#{longitude},#{latitude},#{recordDate})")
    int addSecurityRecord(String incidentId, int resId, int type,
                          double longitude, double latitude, Timestamp recordDate);

    @Select("SELECT MAX(incident_id) FROM security WHERE incident_id LIKE #{todayId}")
    String maxIncidentId(String todayId);

}
