/**
 * Health Class
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

import java.sql.Timestamp;

public class Health {

    private int resId;
    private double height;
    private double weight;
    private int heartRate;
    private int bpSystolic;
    private int bpDiastolic;
    private double bloodGlucose;
    private double bloodLipids;
    private double uricAcid;
    private String suggestion;
    private Timestamp recordDate;

    public Health() {
        super();
    }

    public Health(int resId, double height, double weight,
                  int heartRate, int bpSystolic, int bpDiastolic,
                  double bloodGlucose, double bloodLipids, double uricAcid,
                  String suggestion, Timestamp recordDate) {
        this.resId = resId;
        this.height = height;
        this.weight = weight;
        this.heartRate = heartRate;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.bloodGlucose = bloodGlucose;
        this.bloodLipids = bloodLipids;
        this.uricAcid = uricAcid;
        this.suggestion = suggestion;
        this.recordDate = recordDate;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getBpSystolic() {
        return bpSystolic;
    }

    public void setBpSystolic(int bpSystolic) {
        this.bpSystolic = bpSystolic;
    }

    public int getBpDiastolic() {
        return bpDiastolic;
    }

    public void setBpDiastolic(int bpDiastolic) {
        this.bpDiastolic = bpDiastolic;
    }

    public double getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public double getBloodLipids() {
        return bloodLipids;
    }

    public void setBloodLipids(double bloodLipids) {
        this.bloodLipids = bloodLipids;
    }

    public double getUricAcid() {
        return uricAcid;
    }

    public void setUricAcid(double uricAcid) {
        this.uricAcid = uricAcid;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }
}
