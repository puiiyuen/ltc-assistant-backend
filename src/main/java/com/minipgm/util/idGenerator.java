package com.minipgm.util;

import com.minipgm.health.HealthMapper;
import com.minipgm.message.feedback.FeedbackMapper;
import com.minipgm.message.notice.NoticeMapper;
import com.minipgm.transaction.bill.BillMapper;
import com.minipgm.transaction.payment.PaymentMapper;
import com.minipgm.user.UserMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;

@Service
public class idGenerator {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private HealthMapper healthMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;


    public static int today() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        year = year % 2000 * 10000;
        month *= 100;

        return year + month + day;

    }

    public int newId(int type) {

        int no = userMapper.countAccountByDay('%' + Integer.toString(todayId(type)) + '%');
        if (no > 999) {//daily max 1000
            return -1;
        } else {
            return today() * 10000 + type * 1000 + no;
        }
    }

    private int todayId(int type) {
        return today() * 10 + type;
    }

    public String billId() {
        String maxBillId = billMapper.maxBillId('%' + Integer.toString(idGenerator.today()) + '%');
        if (maxBillId != null) {
            maxBillId = maxBillId.substring(4);
            return "bill" + (Integer.parseInt(maxBillId) + 1);
        } else {
            return "bill" + (idGenerator.today() * 10000 + 1);
        }

    }

    public String paymentId() {
        String maxPaymentId = paymentMapper.maxPaymentId('%' + Integer.toString(idGenerator.today()) + '%');
        if (maxPaymentId != null) {
            maxPaymentId = maxPaymentId.substring(7);
            return "payment" + (Integer.parseInt(maxPaymentId) + 1);
        } else {
            return "payment" + (idGenerator.today() * 10000 + 1);
        }
    }

    public String reportId() {
        String maxReportId = healthMapper.maxReportId('%' + Integer.toString(idGenerator.today()) + '%');
        if (maxReportId != null) {
            maxReportId = maxReportId.substring(6);
            return "report" + (Integer.parseInt(maxReportId) + 1);
        } else {
            return "report" + (idGenerator.today() * 10000 + 1);
        }

    }

    public String noticeId() {
        String maxNoticeId = noticeMapper.maxNoticeId('%' + Integer.toString(idGenerator.today()) + '%');
        if (maxNoticeId != null) {
            maxNoticeId = maxNoticeId.substring(6);
            return "notice" + (Integer.parseInt(maxNoticeId) + 1);
        } else {
            return "notice" + (idGenerator.today() * 10000 + 1);
        }
    }

    public String feedbackId() {
        String maxFeedbackId = feedbackMapper.maxFeedbackId('%' + Integer.toString(idGenerator.today()) + '%');
        if (maxFeedbackId != null) {
            maxFeedbackId = maxFeedbackId.substring(8);
            return "feedback" + (Integer.parseInt(maxFeedbackId) + 1);
        } else {
            return "feedback" + (idGenerator.today() * 10000 + 1);
        }
    }
}
