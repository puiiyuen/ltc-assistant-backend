/**
 * User Service
 *
 * @author Peiyuan
 * 2019-02-24
 */

package com.minipgm.resident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentMapper resident;

    public List<ResidentBase> getResBaseInfo(){

        List<ResidentDetail> resDetailListObj = resident.getRangeResidents(0,20);
        List<ResidentBase> resBaseListObj = new ArrayList<>() ;

        for (ResidentDetail residentDetail : resDetailListObj) {
            ResidentBase resBaseObj = new ResidentBase(residentDetail);
            resBaseListObj.add(resBaseObj);
        }
        return resBaseListObj;
    }

    public ResidentDetail getResDetailById(int userId){
        return resident.getResidentById(userId);
    }
}
