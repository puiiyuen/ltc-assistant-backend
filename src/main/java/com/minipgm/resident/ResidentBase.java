/**
 * ResidentBase Class
 *
 * @author Peiyuan
 * 2019-02-24
 */


package com.minipgm.resident;

import com.minipgm.enums.SexEnum;

import java.util.Date;

public class ResidentBase {

    private int resId;
    private String name;
    private String phone;
    private int numOfBed;
    private SexEnum sex;
    private Date dob;
    private String famName;
    private String famPhone;

    public ResidentBase() {
        super();
    }

    public ResidentBase(Resident res) {
        this.resId = res.getResId();
        this.name = res.getName();
        this.phone = res.getPhone();
        this.numOfBed = res.getNumOfBed();
        this.sex = res.getSex();
        this.dob = res.getDob();
        this.famName = res.getFamName();
        this.famPhone = res.getFamPhone();
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

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public String getFamPhone() {
        return famPhone;
    }

    public void setFamPhone(String famPhone) {
        this.famPhone = famPhone;
    }
}
