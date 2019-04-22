/**
 * Monitor Controller
 *
 * @author Peiyuan
 * 2019-04-18
 */


package com.minipgm.security.monitor;

import com.minipgm.security.location.Location;
import com.minipgm.security.location.LocationService;
import com.minipgm.utils.operationStatus;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class MonitorController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private MonitorService monitorService;

    @PostMapping("/monitor")
    public Object securityMonitor(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return monitorService.getSecurityList();
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @GetMapping("/fence-monitor")
    public Object fenceMonitor(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return monitorService.getUserLocationList();
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/alert")
    public Object getAlert(@RequestBody Map<String,Object> param,HttpSession session){
        try{
            if (sessionCheck.isOnline(session,"ADMIN")){
                String incidentId = param.get("incidentId").toString();
                return monitorService.getAlert(incidentId);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }


    @PostMapping("/sos")
    public Object helpRequest(@RequestBody Map<String,Object> param){
        try {
            Location location = new Location(Integer.parseInt(param.get("userId").toString()),
                    Double.parseDouble(param.get("longitude").toString()),
                    Double.parseDouble(param.get("latitude").toString()),
                    param.get("timestamp").toString());
            return locationService.helpRequest(location);
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/process-incident")
    public int toProcessIncident(@RequestBody Map<String,Object> param,HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                int processStatus = (int)param.get("processStatus");
                String incidentId = param.get("incidentId").toString();
                if (processStatus == 1){
                    int staffId = (int)param.get("staffId");
                    return monitorService.processIncident(incidentId,staffId);
                } else if (processStatus == 2) {
                    int staffId = (int)param.get("staffId");
                    String comment = param.get("comment").toString();
                    return monitorService.finishIncident(staffId,incidentId,comment);
                } else if (processStatus == 3){
                    int staffId = (int) session.getAttribute("userId");
                    return monitorService.ignoreIncident(staffId,incidentId);
                } else {
                    return operationStatus.FAILED;
                }
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }


}
