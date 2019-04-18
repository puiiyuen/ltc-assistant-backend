/**
 * Bill Controller
 *
 * @author Peiyuan
 * 2019-03-15
 */

package com.minipgm.transaction.bill;

import com.minipgm.utils.operationStatus;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/bill-list")
    public List<BillList> getBillList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return billService.getBillList();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/detail")
    public List<BillDTO> getBillDetail(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return billService.getBillDetail(Integer.parseInt(param.get("resId").toString()));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/search")
    public List<BillList> searchBill(@RequestBody Map<String,Object> param, HttpSession session){
        try {
            int resId,numOfBed;
            String resName;
            if (sessionCheck.isOnline(session,"ADMIN")) {
                String searchInput = param.get("search").toString();
                if (searchInput.equals("")){
                    return null;
                }
                if (searchInput.matches("^[0-9]+$")){
                    resId = Integer.parseInt(searchInput);
                    numOfBed = Integer.parseInt(searchInput);
                    return billService.searchBills(resId,"noname",numOfBed);
                } else {
                    resName = searchInput;
                    return billService.searchBills(-1,resName,-1);
                }
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/add-record")
    public int addBillRecord(@RequestBody Map<String, Object> param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return billService.addBillRecord(Integer.parseInt(param.get("resId").toString()),
                        param.get("item").toString(),Double.parseDouble(param.get("amount").toString()));
            }else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/modify-bill")
    public int modifyBillRecord(@RequestBody Map<String,Object> param,HttpSession session){
        try{
            if (sessionCheck.isOnline(session,"ADMIN")){
                Bill modifyBill = new Bill(Integer.parseInt(param.get("resId").toString()),
                        param.get("billId").toString(),param.get("item").toString(),
                        Double.parseDouble(param.get("amount").toString()), Timestamp.valueOf("2099-12-31 00:00:00"));
                return billService.modifyBillRecord(modifyBill);
            } else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete-record")
    public int deleteBillRecord(@RequestBody Map<String,Object> param,HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return billService.deleteBillRecord(Integer.parseInt(param.get("resId").toString()),
                        param.get("billId").toString());
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
