/**
 * Payment Mapper
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaymentMapper {

    @Results({
            @Result(property = "resId",column = "res_id"),
            @Result(property = "paymentId",column = "payment_id"),
            @Result(property = "paid",column = "paid"),
            @Result(property = "platform",column = "platform"),
            @Result(property = "recordDate",column = "record_date")
    })
    @Select("SELECT * FROM payment WHERE res_id =#{resId} ORDER BY record_date DESC ")
    List<Payment> getPaymentDetail(int resId);

    @Select("SELECT MAX(payment_id) FROM payment WHERE payment_id LIKE #{todayId}")
    String maxPaymentId(String todayId);

}
