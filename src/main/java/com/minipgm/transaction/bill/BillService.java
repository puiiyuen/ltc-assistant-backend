/**
 * Bill Service
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import com.minipgm.utils.idGenerator;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private idGenerator idGenerator;

    public List<BillList> getBillList() {
        return billMapper.getBillList();
    }

    public List<BillDTO> getBillDetail(int resId) {
        return billMapper.getBillDetail(resId);
    }

    public List<BillList> searchBills(int resId, String name, int numOfBed) {
        return billMapper.searchBills(resId, name, numOfBed);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addBillRecord(int resId, String item, double amount) {
        try {
            String billId = idGenerator.billId();
            billMapper.addBillRecord(resId, billId, item, amount);
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyBillRecord(Bill bill) {
        try {
            if (billMapper.modifyBillRecord(bill.getResId(), bill.getBillId(), bill.getItem(), bill.getAmount()) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteBillRecord(int resId, String billId) {
        try {
            if (billMapper.deleteBillRecord(resId, billId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }

    }
}
