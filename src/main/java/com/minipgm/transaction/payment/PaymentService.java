/**
 * Payment Service
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.payment;

import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public List<Payment> getPaymentDetail(int resId){
        return paymentMapper.getPaymentDetail(resId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addPaymentRecord(Payment payment){
        try {
            paymentMapper.addPaymentRecord(payment.getResId(),payment.getPaymentId(),
                    payment.getPlatform(),payment.getPaid());
            return operationStatus.SUCCESSFUL;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyPaymentRecord(Payment payment){
        try {
            if (paymentMapper.modifyPaymentRecord(payment.getResId(),payment.getPaymentId(),payment.getPaid()) == 1){
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deletePaymentRecord(int resId,String paymentId){
        try {
            if (paymentMapper.deletePaymentRecord(resId,paymentId) == 1 ){
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }
}
