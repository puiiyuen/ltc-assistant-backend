/**
 * Resident Class
 *
 * @author Peiyuan
 * 2019-02-24
 */


package com.minipgm.resident;

import com.minipgm.enums.SexEnum;

import java.util.Date;

public class Resident {

    private int resId;
    private int familyId;
    private String name;
    private String goverId;
    private String phone;
    private String email;
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

    public Resident() {
        super();
    }

    public Resident(int resId, int familyId, String name, String goverId, String phone, String email, int numOfBed,
                    SexEnum sex, Date dob, String address, String photoUrl, String egName, String egPhone,
                    String medicalHistory, Date moveInDate, Date moveOutDate) {
        this.resId = resId;
        this.familyId = familyId;
        this.name = name;
        this.goverId = goverId;
        this.phone = phone;
        this.email = email;
        this.numOfBed = numOfBed;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
        this.photoUrl = photoUrl;
        this.egName = egName;
        this.egPhone = egPhone;
        this.medicalHistory = medicalHistory;
        this.moveInDate = moveInDate;
        this.moveOutDate = moveOutDate;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
