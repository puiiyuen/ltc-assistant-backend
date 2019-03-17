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

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    public int addHealthRecord(Map<String, Object> data) {
        try {
            Health healthReport = new Health(Integer.parseInt(data.get("resId").toString()),
                    Double.parseDouble(data.get("height").toString()),
                    Double.parseDouble(data.get("weight").toString()),
                    Integer.parseInt(data.get("heartRate").toString()),
                    Integer.parseInt(data.get("bpSystolic").toString()),
                    Integer.parseInt(data.get("bpDiastolic").toString()),
                    Double.parseDouble(data.get("bloodGlucose").toString()),
                    Double.parseDouble(data.get("bloodLipids").toString()),
                    Double.parseDouble(data.get("uricAcid").toString()),
                    data.get("suggestion").toString(), Timestamp.valueOf("2099-12-31 00:00:00"));
            healthMapper.addHealthRecord(healthReport.getResId(),healthReport.getHeight(),healthReport.getWeight(),
                    healthReport.getHeartRate(),healthReport.getBpSystolic(),healthReport.getBpDiastolic(),
                    healthReport.getBloodGlucose(),healthReport.getBloodLipids(),healthReport.getUricAcid(),
                    healthReport.getSuggestion());
            return operationStatus.SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
}
