/**
 * Monitor Service
 *
 * @author Peiyuan
 * 2019-04-18
 */


package com.minipgm.security.monitor;

import com.minipgm.security.location.LocationMapper;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private LocationMapper locationMapper;

    public Object getUserLocationList(){
        try {
            return locationMapper.getUserLocation();
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object getSecurityList() {
        try {
            List<SecurityDTO> securityList = monitorMapper.getActivatedSecurityList();
            securityList.addAll(monitorMapper.getFinishedSecurityList(0,20));
            return securityList;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }



}
