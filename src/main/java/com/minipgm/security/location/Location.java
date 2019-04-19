/**
 * Location Class
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.security.location;

import java.sql.Timestamp;

public class Location {

    private int userId;
    private double longitude;
    private double latitude;
    private String unixTs;
    private Timestamp recordDate;

    public Location() {
        super();
    }

    public Location(int userId, double longitude, double latitude, String unixTs, Timestamp recordDate) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.unixTs = unixTs;
        this.recordDate = recordDate;
    }

    public Location(int userId, double longitude, double latitude, String unixTs) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.unixTs = unixTs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUnixTs() {
        return unixTs;
    }

    public void setUnixTs(String unixTs) {
        this.unixTs = unixTs;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }
}
