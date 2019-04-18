/**
 * Payment Controller
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import com.minipgm.enums.PaymentPlatformEnum;
import com.minipgm.utils.idGenerator;
import com.minipgm.utils.operationStatus;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private idGenerator idGenerator;

    @PostMapping("/detail")
    public List<Payment> getPaymentDetail(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return paymentService.getPaymentDetail(Integer.parseInt(param.get("resId").toString()));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/add-record")
    public int addPaymentRecord(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                Payment paymentRecord = new Payment(Integer.parseInt(param.get("resId").toString()),
                        idGenerator.paymentId(), Double.parseDouble(param.get("paid").toString()),
                        PaymentPlatformEnum.valueOf(param.get("platform").toString()),
                        Timestamp.valueOf("2099-12-31 00:00:00"));
                return paymentService.addPaymentRecord(paymentRecord);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/modify-record")
    public int modifyPaymentRecord(@RequestBody Map<String , Object> param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                Payment payment = new Payment(Integer.parseInt(param.get("resId").toString()),
                        param.get("paymentId").toString(),Double.parseDouble(param.get("paid").toString()),
                        PaymentPlatformEnum.NOT_AVAILABLE,Timestamp.valueOf("2099-12-31 00:00:00"));
                return paymentService.modifyPaymentRecord(payment);
            }else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete-record")
    public int deletePaymentRecord(@RequestBody Map<String,Object> param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return paymentService.deletePaymentRecord(Integer.parseInt(param.get("resId").toString()),
                        param.get("paymentId").toString());
            }else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            return operationStatus.SERVERERROR;
        }
    }
}
