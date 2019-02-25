/**
 * ResidentBase Class
 *
 * @author Peiyuan
 * 2019-02-24
 */


package com.minipgm.resident;

import com.minipgm.user.enums.SexEnum;

import java.util.Date;

public class ResidentBase {

    private int resId;
    private String name;
    private String phone;
    private int numOfBed;
    private SexEnum sex;
    private Date dob;
    private String egName;
    private String egPhone;

    public ResidentBase() {
        super();
    }

    public ResidentBase(ResidentDetail res) {
        this.resId = res.getResId();
        this.name = res.getName();
        this.phone = res.getPhone();
        this.numOfBed = res.getNumOfBed();
        this.sex = res.getSex();
        this.dob = res.getDob();
        this.egName = res.getEgName();
        this.egPhone = res.getEgPhone();
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
