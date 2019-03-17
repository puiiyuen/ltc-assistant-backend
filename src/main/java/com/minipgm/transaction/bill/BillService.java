/**
 * Bill Service
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillMapper billMapper;

    public List<BillList> getBillList(){
        return billMapper.getBillList();
    }

    public List<BillDTO> getBillDetail(int resId){
        return billMapper.getBillDetail(resId);
    }

    public List<BillList> searchBills(int resId,String name,int numOfBed){
        return billMapper.searchBills(resId,name,numOfBed);
    }
}
