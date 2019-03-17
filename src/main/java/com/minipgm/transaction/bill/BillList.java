package com.minipgm.transaction.bill;

import java.sql.Timestamp;

public class BillList {
    private int resId;
    private String name;
    private int numOfBed;
    private double totalBill;
    private double totalPaid;
    private Timestamp latestBillDate;

    public BillList(){
        super();
    }

    public BillList(int resId, String name, int numOfBed, double totalBill, double totalPaid, Timestamp latestBillDate) {
        this.resId = resId;
        this.name = name;
        this.numOfBed = numOfBed;
        this.totalBill = totalBill;
        this.totalPaid = totalPaid;
        this.latestBillDate = latestBillDate;
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

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Timestamp getLatestBillDate() {
        return latestBillDate;
    }

    public void setLatestBillDate(Timestamp latestBillDate) {
        this.latestBillDate = latestBillDate;
    }
}
