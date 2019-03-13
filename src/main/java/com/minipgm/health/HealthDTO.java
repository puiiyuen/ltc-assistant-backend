package com.minipgm.health;

import com.minipgm.enums.SexEnum;

import java.sql.Timestamp;
import java.util.Date;

public class HealthDTO extends Health {

    private String name;
    private SexEnum sex;
    private Date dob;
    private int numOfBed;
    private String medicalHistory;

    public HealthDTO() {
        super();
    }

    public HealthDTO(String name, SexEnum sex, Date dob, int numOfBed, String medicalHistory,
                     int resId, double height, double weight, int heartRate,
                     int bpSystolic, int bpDiastolic, double bloodGlucose,
                     double bloodLipids, double uricAcid, String suggestion,
                     Timestamp recordDate) {
        super(resId, height, weight, heartRate, bpSystolic, bpDiastolic,
                bloodGlucose, bloodLipids, uricAcid, suggestion, recordDate);
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.numOfBed = numOfBed;
        this.medicalHistory = medicalHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
