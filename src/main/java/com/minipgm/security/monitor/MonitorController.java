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
    public Object securityMonitor(@RequestBody Map<String,Object> param ,HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                locationService.fenceCheck(param.get("gid").toString());
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


}
