/**
 * Health Service
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.List;

@Service
public class HealthService {

    @Autowired
    private HealthMapper healthMapper;

    public List<HealthDTO> getHealthReportList() {
        return healthMapper.getHealthReportList();
    }

    public List<HealthDTO> getReportById(int resId) {
        return healthMapper.getReportById(resId);
    }

    public List<HealthDTO> searchHealthReport(int resId, String name, int numOfBed) {
        return healthMapper.searchHealthReport(resId, name, numOfBed);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addHealthRecord(Health healthReport) {
        try {
            healthMapper.addHealthRecord(healthReport.getResId(), healthReport.getReportId(),
                    healthReport.getHeight(), healthReport.getWeight(),
                    healthReport.getHeartRate(), healthReport.getBpSystolic(), healthReport.getBpDiastolic(),
                    healthReport.getBloodGlucose(), healthReport.getBloodLipids(), healthReport.getUricAcid(),
                    healthReport.getSuggestion());
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int modifyHealthRecord(Health healthReport) {
        try {
            if (healthMapper.modifyHealthRecord(healthReport.getResId(), healthReport.getReportId(),
                    healthReport.getHeight(), healthReport.getWeight(), healthReport.getHeartRate(),
                    healthReport.getBpSystolic(), healthReport.getBpDiastolic(), healthReport.getBloodGlucose(),
                    healthReport.getBloodLipids(), healthReport.getUricAcid(), healthReport.getSuggestion()) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteHealthRecord(int resId, String reportId) {
        try {
            if (healthMapper.deleteHealthRecord(resId, reportId) == 1) {
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
