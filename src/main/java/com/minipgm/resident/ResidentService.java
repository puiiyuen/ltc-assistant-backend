/**
 * User Service
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;

import com.minipgm.enums.UserTypeEnum;
import com.minipgm.user.UserMapper;
import com.minipgm.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResidentService {

    @Autowired
    private ResidentMapper residentMapper;
    private UserMapper userMapper;

    public List<ResidentBase> getResBaseInfo() {

        List<ResidentDetail> resDetailListObj = residentMapper.getRangeResidents(0, 20);
        List<ResidentBase> resBaseListObj = new ArrayList<>();

        for (ResidentDetail residentDetail : resDetailListObj) {
            ResidentBase resBaseObj = new ResidentBase(residentDetail);
            resBaseListObj.add(resBaseObj);
        }
        return resBaseListObj;
    }

    public ResidentDetail getResDetailById(int userId) {
        return residentMapper.getResidentById(userId);
    }

    public int addResident(Map<String, Object> data) {
        int resId = idGenerator.newId(1);
        int famId = idGenerator.newId(2);
        try {
            if (residentMapper.existResident(data.get("goverId").toString()) == null) {
                if (userMapper.createAccount(resId,data.get("name").toString(),
                        UserTypeEnum.RESIDENT,regCodeGenerator.newRegCode())==1){
                    if (userMapper.createAccount(famId,data.get("famName").toString(),
                            UserTypeEnum.RESFAMILY,regCodeGenerator.newRegCode())==1){
                            residentMapper.createResident();
                            residentMapper.createResidentFamily();
                    }else {
                        return operationStatus.FAILED;
                    }
                }else{
                    return operationStatus.FAILED;
                }
            } else {
                return operationStatus.ISEXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
}
