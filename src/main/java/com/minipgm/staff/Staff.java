/**
 * Staff Class
 *
 * @author Peiyuan
 * 2019-04-08
 */


package com.minipgm.staff;

import com.minipgm.enums.SexEnum;

import java.sql.Timestamp;
import java.util.Date;

public class Staff {

    private int staffId;
    private String name;
    private String goverId;
    private String phone;
    private String email;
    private SexEnum sex;
    private Date dob;
    private String address;
    private String photoUrl;
    private Date moveInDate;
    private int status;
    private Timestamp createDate;
    private Timestamp updateDate;

    public Staff() {
        super();
    }

    public Staff(int staffId, String name, String goverId, String phone,
                 String email, SexEnum sex, Date dob, String address,
                 String photoUrl,Date moveInDate) {
        this.staffId = staffId;
        this.name = name;
        this.goverId = goverId;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
        this.photoUrl = photoUrl;
        this.moveInDate = moveInDate;
    }

    public Staff(int staffId, String name, String goverId, String phone,
                 String email, SexEnum sex, Date dob, String address,
                 String photoUrl, Date moveInDate, int status,
                 Timestamp createDate, Timestamp updateDate) {
        this.staffId = staffId;
        this.name = name;
        this.goverId = goverId;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
        this.photoUrl = photoUrl;
        this.moveInDate = moveInDate;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
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

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
