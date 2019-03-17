/**
 * Payment Class
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import com.minipgm.enums.PaymentPlatformEnum;

import java.sql.Timestamp;

public class Payment {
    private int resId;
    private double paid;
    private PaymentPlatformEnum platform;
    private Timestamp recordDate;

    public Payment(){
        super();
    }

    public Payment(int resId, double paid, PaymentPlatformEnum platform, Timestamp recordDate) {
        this.resId = resId;
        this.paid = paid;
        this.platform = platform;
        this.recordDate = recordDate;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public PaymentPlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PaymentPlatformEnum platform) {
        this.platform = platform;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }
}
