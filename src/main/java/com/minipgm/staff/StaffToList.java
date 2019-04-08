package com.minipgm.staff;

import com.minipgm.enums.SexEnum;

import java.util.Date;

public class StaffToList {

    private int staffId;
    private String name;
    private SexEnum sex;
    private Date dob;
    private int status;

    public StaffToList() {
        super();
    }

    public StaffToList(int staffId, String name, SexEnum sex, Date dob, int status) {
        this.staffId = staffId;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.status = status;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
