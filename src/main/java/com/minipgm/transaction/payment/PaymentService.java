/**
 * Payment Service
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public List<Payment> getPaymentDetail(int resId){
        return paymentMapper.getPaymentDetail(resId);
    }

}
