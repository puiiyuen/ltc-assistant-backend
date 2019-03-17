/**
 * Health Controller
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

import com.minipgm.util.operationStatus;
import com.minipgm.util.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/base-info")
    public List<HealthDTO> getHealthReportList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")) {
                return healthService.getHealthReportList();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/report-detail")
    public List<HealthDTO> getReportDetailById(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")) {
                return healthService.getReportById(Integer.parseInt(param.get("resId").toString()));
            }else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/add-record")
    public int addHealthRecord(@RequestBody Map<String, Object> param,HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return healthService.addHealthRecord(param);
            }else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/search")
    public List<HealthDTO> searchHealthReport(@RequestBody Map<String,Object> param ,HttpSession session) {
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
                    return healthService.searchHealthReport(resId,"noname",numOfBed);
                } else {
                    resName = searchInput;
                    return healthService.searchHealthReport(-1,resName,-1);
                }
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
