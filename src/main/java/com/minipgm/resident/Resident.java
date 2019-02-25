/**
 * Resident Class
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;


import com.minipgm.user.enums.SexEnum;

import java.sql.Date;

public class Resident {

    private int resId;
    private int familyId;
    private String name;
    private String goverId;
    private int numOfBed;
    private SexEnum sex;
    private Date dob;
    private String address;
    private String photoUrl;
    private String egName;
    private String egPhone;
    private String medicalHistory;
    private Date moveInDate;
    private Date moveOutDate;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoverId() {
        return goverId;
    }

    public void setGoverId(String goverId) {
        this.goverId = goverId;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEgName() {
        return egName;
    }

    public void setEgName(String egName) {
        this.egName = egName;
    }

    public String getEgPhone() {
        return egPhone;
    }

    public void setEgPhone(String egPhone) {
        this.egPhone = egPhone;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public Date getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(Date moveOutDate) {
        this.moveOutDate = moveOutDate;
    }
}
