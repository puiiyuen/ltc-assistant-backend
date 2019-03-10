/**
 * User Service
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;

import com.minipgm.enums.*;
import com.minipgm.user.UserService;
import com.minipgm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.nio.file.*;
import java.sql.Date;
import java.util.*;

@Service
public class ResidentService {

    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private regCodeGenerator regCodeGenerator;
    @Autowired
    private idGenerator idGenerator;

    public List<ResidentBase> getResBaseInfo() {

        List<Resident> resListObj = residentMapper.getRangeResidents(0, 20);
        List<ResidentBase> resBaseListObj = new ArrayList<>();

        for (Resident resident : resListObj) {
            ResidentBase resBaseObj = new ResidentBase(resident);
            resBaseListObj.add(resBaseObj);
        }
        return resBaseListObj;
    }

    public Resident getResDetailById(int userId) {
        return residentMapper.getResidentById(userId);
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int addResident(Map<String, Object> data) {

        try {
            int resId = idGenerator.newId(1);
            int famId = idGenerator.newId(2);
            int resRegcode = regCodeGenerator.newRegCode();
            int famRegcode = regCodeGenerator.newRegCode();

            if (resId == -1 || famId == -1 || resRegcode == -1 || famRegcode == -1) {
                return operationStatus.FAILED;
            }

            Resident newRes = new Resident(resId, famId, data.get("name").toString(), data.get("goverId").toString(),
                    data.get("phone").toString(), data.get("email").toString(),
                    Integer.parseInt(data.get("bed").toString()), SexEnum.valueOf(data.get("sex").toString()),
                    Date.valueOf(data.get("dob").toString()), data.get("address").toString(),
                    data.get("famName").toString(), data.get("famPhone").toString(), data.get("famEmail").toString(),
                    data.get("famAddress").toString(), data.get("medicalHistory").toString(),
                    Date.valueOf(data.get("moveInDate").toString()));
//            if (residentMapper.existResident(newRes.getGoverId()) == null) {
//                if (userMapper.createAccount(newRes.getResId(),newRes.getName(),
//                        UserTypeEnum.RESIDENT,regCodeGenerator.newRegCode())==1){
//                    if (userMapper.createAccount(famId,newRes.getEgName(),
//                            UserTypeEnum.RESFAMILY,regCodeGenerator.newRegCode())==1){
//                            residentMapper.createResident(newRes.getResId(),newRes.getName(),newRes.getSex(),
//                                    newRes.getDob(),newRes.getNumOfBed(),newRes.getGoverId(),newRes.getAddress(),
//                                    newRes.getEgName(),newRes.getEgPhone(),newRes.getFamilyId(),newRes.getMoveInDate(),
//                                    newRes.getMedicalHistory());
//                            residentMapper.createResidentFamily();
//                    }else {
//                        return operationStatus.FAILED;
//                    }
//                }else{
//                    return operationStatus.FAILED;
//                }
//            } else {
//                return operationStatus.ISEXIST;
//            }
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


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public int updatePhotoById(byte[] photo, String photoName, String goverId) {
        try {
            String UPLOAD_FOLDER = "/opt/photo/";
            Path path = Paths.get(UPLOAD_FOLDER + photoName);
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, photo);
            String photoUrl = "http://localhost:8080/photo/" + photoName;
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
}
