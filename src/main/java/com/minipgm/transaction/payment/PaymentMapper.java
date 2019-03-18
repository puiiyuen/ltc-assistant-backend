/**
 * Payment Mapper
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import com.minipgm.enums.PaymentPlatformEnum;
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

    @Insert("INSERT INTO payment (res_id,payment_id,platform,paid) " +
            "VALUES(#{resId},#{paymentId},#{platform},#{paid})")
    int addPaymentRecord(int resId, String paymentId, PaymentPlatformEnum platform,double paid);

    @Update("UPDATE payment SET paid=#{paid},record_date=CURRENT_TIMESTAMP" +
            " WHERE res_id=#{resId} AND payment_id=#{paymentId}")
    int modifyPaymentRecord(int resId,String paymentId,double paid);

    @Delete("DELETE FROM payment WHERE res_id=#{resId} AND payment_id=#{paymentId}")
    int deletePaymentRecord(int resId,String paymentId);

}
