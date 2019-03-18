/**
 * Bill Class
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import java.sql.Timestamp;

public class Bill {
    private int resId;
    private String billId;
    private String item;
    private double amount;
    private Timestamp recordDate;

    public Bill(){
        super();
    }

    public Bill(int resId, String billId, String item, double amount, Timestamp recordDate) {
        this.resId = resId;
        this.billId = billId;
        this.item = item;
        this.amount = amount;
        this.recordDate = recordDate;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }
}
