/**
 * Monitor Service
 *
 * @author Peiyuan
 * 2019-04-18
 */


package com.minipgm.security.monitor;

import com.minipgm.security.location.LocationMapper;
import com.minipgm.staff.StaffStatus;
import com.minipgm.utils.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;
    @Autowired
    private LocationMapper locationMapper;

    public Object getUserLocationList() {
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
            securityList.addAll(monitorMapper.getFinishedSecurityList(0, 20));
            return securityList;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object getAlert(String incidentId) {
        try {
            return monitorMapper.getAlert(incidentId);
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int processIncident(String incidentId, int staffId) {
        try {
            if (monitorMapper.changeStaffStatus(staffId, StaffStatus.BUSY) == 1 &&
                    monitorMapper.processIncident(incidentId, staffId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int finishIncident(int staffId, String incidentId, String comment) {
        try {
            if (monitorMapper.changeStaffStatus(staffId, StaffStatus.ONDUTY) == 1 &&
                    monitorMapper.finishIncident(incidentId, comment) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int ignoreIncident(int staffId,String incidentId){
        try {
            if (monitorMapper.ignoreIncident(incidentId,staffId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }


}
