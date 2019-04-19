package com.minipgm.security.monitor;

import java.sql.Timestamp;

public class SecurityDTO {

    private String incidentId;
    private int resId;
    private int type;
    private double longitude;
    private double latitude;
    private Timestamp recordTime;
    private int staffId;
    private int processStatus;
    private Timestamp processTime;
    private Timestamp finishTime;
    private String comment;

    public SecurityDTO() {
        super();
    }

    public SecurityDTO(String incidentId, int resId, int type, double longitude, double latitude,
                       Timestamp recordTime, int staffId, int processStatus,
                       Timestamp processTime, Timestamp finishTime, String comment) {
        this.incidentId = incidentId;
        this.resId = resId;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.recordTime = recordTime;
        this.staffId = staffId;
        this.processStatus = processStatus;
        this.processTime = processTime;
        this.finishTime = finishTime;
        this.comment = comment;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public Timestamp getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Timestamp processTime) {
        this.processTime = processTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
