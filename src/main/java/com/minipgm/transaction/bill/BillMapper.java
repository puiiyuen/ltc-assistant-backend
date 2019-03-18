/**
 * Bill Mapper
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BillMapper {

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "numOfBed",column = "num_bed"),
            @Result(property = "resId",column = "res_id"),
            @Result(property = "totalBill",column = "total_bill"),
            @Result(property = "totalPaid",column = "total_paid"),
            @Result(property = "latestBillDate",column = "latest_bill_date")
    })
    @Select("SELECT res.res_id,res.name,res.num_bed,bi.total_bill,pa.total_paid,bi.latest_bill_date " +
            "FROM res_profile res LEFT OUTER JOIN(SELECT b.res_id,SUM(b.amount) AS total_bill," +
            "MAX(b.record_date) AS latest_bill_date FROM bill b GROUP BY b.res_id) bi " +
            "ON res.res_id=bi.res_id LEFT OUTER JOIN (SELECT p.res_id,SUM(p.paid) AS total_paid " +
            "FROM payment p GROUP BY p.res_id) pa ON res.res_id=pa.res_id " +
            "ORDER BY bi.latest_bill_date DESC LIMIT 0,20")
    List<BillList> getBillList();

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "numOfBed",column = "num_bed"),
            @Result(property = "resId",column = "res_id"),
            @Result(property = "totalBill",column = "total_bill"),
            @Result(property = "totalPaid",column = "total_paid"),
            @Result(property = "latestBillDate",column = "latest_bill_date")
    })
    @Select("SELECT res.res_id,res.name,res.num_bed,bi.total_bill,pa.total_paid,bi.latest_bill_date " +
            "FROM res_profile res LEFT OUTER JOIN(SELECT b.res_id,SUM(b.amount) AS total_bill," +
            "MAX(b.record_date) AS latest_bill_date FROM bill b GROUP BY b.res_id) bi " +
            "ON res.res_id=bi.res_id LEFT OUTER JOIN (SELECT p.res_id,SUM(p.paid) AS total_paid " +
            "FROM payment p GROUP BY p.res_id) pa ON res.res_id=pa.res_id " +
            "WHERE res.res_id=#{resId} OR res.name=#{name} OR res.num_bed=#{numOfBed} "+
            "ORDER BY bi.latest_bill_date DESC LIMIT 0,20")
    List<BillList> searchBills(int resId,String name,int numOfBed);

    @Results({
            @Result(property = "name",column = "name"),
            @Result(property = "numOfBed",column = "num_bed"),
            @Result(property = "resId",column = "res_id"),
            @Result(property = "billId",column = "bill_id"),
            @Result(property = "item",column = "item"),
            @Result(property = "amount",column = "amount"),
            @Result(property = "recordDate",column = "record_date")
    })
    @Select("SELECT res.res_id,res.name,res.num_bed,bi.bill_id,bi.item,bi.amount,bi.record_date " +
            "FROM res_profile res LEFT OUTER JOIN bill bi ON res.res_id=bi.res_id " +
            "WHERE res.res_id = #{resId} ORDER BY bi.record_date DESC")
    List<BillDTO> getBillDetail(int resId);


    @Select("SELECT MAX(bill_id) FROM bill WHERE bill_id LIKE #{todayId}")
    String maxBillId(String todayId);

    @Insert("INSERT INTO bill (res_id,bill_id,item,amount) VALUES (#{resId},#{billId},#{item},#{amount})")
    int addBillRecord(int resId,String billId,String item,double amount);

    @Update("Update bill SET item=#{item},amount=#{amount},record_date=CURRENT_TIMESTAMP" +
            " WHERE bill_id=#{billId} AND res_id=#{resId}")
    int modifyBillRecord(int resId,String billId,String item,double amount);

    @Delete("DELETE FROM bill WHERE res_id=#{resId} AND bill_id=#{billId}")
    int deleteBillRecord(int resId,String billId);
}
