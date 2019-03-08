/**
 * User Service
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;

import com.minipgm.enums.*;
import com.minipgm.user.UserMapper;
import com.minipgm.user.UserService;
import com.minipgm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Transactional//!!! [Working]Roll back !!!
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
                    data.get("phone").toString(), data.get("email").toString(), Integer.parseInt(data.get("bed").toString()),
                    SexEnum.valueOf(data.get("sex").toString()), Date.valueOf(data.get("dob").toString()),
                    data.get("address").toString(), "default photo", data.get("famName").toString(),
                    data.get("famPhone").toString(), data.get("medicalHistory").toString(),
                    Date.valueOf(data.get("moveInDate").toString()), Date.valueOf("2099-12-31"));
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
                userService.createAccount(newRes.getResId(),newRes.getName(),UserTypeEnum.RESIDENT,resRegcode);
                userService.createAccount(newRes.getFamilyId(),newRes.getEgName(),UserTypeEnum.RESFAMILY,famRegcode);
                residentMapper.createResident(newRes.getResId(), newRes.getName(), newRes.getSex(),
                        newRes.getDob(), newRes.getNumOfBed(), newRes.getGoverId(), newRes.getAddress(),
                        newRes.getEgName(), newRes.getEgPhone(), newRes.getFamilyId(), newRes.getMoveInDate(),
                        newRes.getMedicalHistory());
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
}
