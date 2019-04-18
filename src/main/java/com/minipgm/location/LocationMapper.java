/**
 * Location Mapper
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.location;

import org.apache.ibatis.annotations.*;

@Mapper
public interface LocationMapper {

    @Insert("INSERT INTO location (user_id,longitude,latitude,unix_ts) " +
            "VALUES(#{userId},#{longitude},#{latitude},#{unixTs})")
    int addLocation(int userId,double longitude,double latitude,String unixTs);

    @Insert("INSERT INTO geofence (user_id,gid,longitude,latitude) " +
            "VALUES(#{userId},#{gid},#{longitude},#{latitude})")
    int addFencePoint(int userId,String gid,double longitude,double latitude);

    @Delete("DELETE FROM geofence WHERE gid=#{gid}")
    int deleteFencePoint(String gid);

}
