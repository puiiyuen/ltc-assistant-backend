/**
 * User Service
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;

import com.minipgm.enums.*;
import com.minipgm.transaction.bill.BillList;
import com.minipgm.transaction.bill.BillMapper;
import com.minipgm.transaction.payment.PaymentMapper;
import com.minipgm.user.UserService;
import com.minipgm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.nio.file.*;
import java.util.*;

@Service
public class ResidentService {

    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private PaymentMapper paymentMapper;


    private List<ResidentBase> infoPack(List<Resident> resListObj) {
        List<ResidentBase> resBaseListObj = new ArrayList<>();

        for (Resident resident : resListObj) {
            ResidentBase resBaseObj = new ResidentBase(resident);
            resBaseListObj.add(resBaseObj);
        }
        return resBaseListObj;
    }

    public List<ResidentBase> getResBaseInfo() {
        List<Resident> resListObj = residentMapper.getRangeResidents(0, 20);
        return infoPack(resListObj);

    }

    public List<ResidentBase> searchResidents(int resId, String name, int numOfBed) {
        List<Resident> residentList = residentMapper.searchResident(resId, name, numOfBed);
        return infoPack(residentList);

    }

    public Resident getResDetailById(int userId) {
        return residentMapper.getResidentById(userId);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addResident(Resident newRes, int resRegcode, int famRegcode) {
        try {
            if (residentMapper.existResident(newRes.getGoverId()) == null) {
                userService.createAccount(newRes.getResId(), "住户" + newRes.getResId(),
                        UserTypeEnum.RESIDENT, resRegcode, newRes.getPhone(), newRes.getEmail());
                userService.createAccount(newRes.getFamilyId(), "家属" + newRes.getFamilyId(),
                        UserTypeEnum.RESFAMILY, famRegcode, newRes.getFamPhone(), newRes.getFamEmail());
                residentMapper.createResident(newRes.getResId(), newRes.getName(), newRes.getSex(),
                        newRes.getDob(), newRes.getNumOfBed(), newRes.getGoverId(), newRes.getAddress(),
                        newRes.getFamilyId(), newRes.getMoveInDate(),
                        newRes.getMedicalHistory());
                residentMapper.createResidentFamily(newRes.getFamilyId(), newRes.getFamName(), newRes.getFamAddress());
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updatePhotoById(byte[] photo, String photoName, String goverId) {
        try {
            String UPLOAD_FOLDER = "/opt/photo/";
//            development
            String api = "http://localhost:8080";
//            live
//            String api = "";
            Path path = Paths.get(UPLOAD_FOLDER + photoName);
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, photo);
            String photoUrl = api + "/photo/" + photoName;
            if (residentMapper.updatePhotoByGoverId(photoUrl, goverId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyResident(Resident resident) {
        try {
            if (residentMapper.modifyResident(resident.getResId(), resident.getName(), resident.getSex(),
                    resident.getDob(), resident.getNumOfBed(), resident.getGoverId(), resident.getAddress(),
                    resident.getFamilyId(), resident.getMoveInDate(), resident.getMedicalHistory()) == 1 &&
                    residentMapper.modifyResidentFamily(resident.getFamilyId(), resident.getFamName()
                            , resident.getFamAddress()) == 1 && userService.modifyContact(resident.getResId(),
                    resident.getPhone(), resident.getEmail()) == operationStatus.SUCCESSFUL &&
                    userService.modifyContact(resident.getFamilyId(), resident.getFamPhone(),
                            resident.getFamEmail()) == operationStatus.SUCCESSFUL) {
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
    public int destroyResident(int resId, int famId) {
        try {
            List<BillList> billCheck = billMapper.searchBills(resId, "noname", -1);
            double balance = billCheck.get(0).getTotalBill() - billCheck.get(0).getTotalPaid();
            if (balance == 0) {
                residentMapper.deleteResidentFamily(famId);
                residentMapper.deleteResident(resId);
                billMapper.deleteBillRecordById(resId);
                paymentMapper.deletePaymentRecordById(resId);
                userService.destroyAccount(famId);
                userService.destroyAccount(resId);
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.BILL;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//Manual transaction rollback
            return operationStatus.SERVERERROR;
        }
    }

}
