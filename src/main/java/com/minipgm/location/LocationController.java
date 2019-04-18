/**
 * Location Controller
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.location;

import com.minipgm.utils.operationStatus;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/collect")
    public int collectLocations(@RequestBody List<Map<String, Object>> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "RESIDENT")) {
                List<Location> locationList = new ArrayList<>();
                for (Map<String, Object> item : param) {
                    Location location = new Location(Integer.parseInt(item.get("userId").toString()),
                            Double.parseDouble(item.get("longitude").toString()),
                            Double.parseDouble(item.get("latitude").toString()),
                            item.get("timestamp").toString());
                    locationList.add(location);
                }
                return locationService.collectLocations(locationList);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @GetMapping("/geofence")
    public Object getUserLocation(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return new ArrayList<>();
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/create-geofence")
    public Object createGeoFence(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                int userId = (int) session.getAttribute("userId");
                String gid = param.get("gid").toString();
                List<List<Double>> points = (List<List<Double>>) param.get("points");
                return locationService.createGeoFence(userId,gid,points);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/update-geofence")
    public Object updateGeoFence(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                int userId = (int) session.getAttribute("userId");
                String gid = param.get("gid").toString();
                List<List<Double>> points = (List<List<Double>>) param.get("points");
                locationService.updateGeoFence(userId,gid,points);
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete-geofence")
    public Object deleteGeoFence(@RequestBody Map<String, Object > param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                locationService.deleteGeoFence(param.get("gid").toString());
                return operationStatus.SUCCESSFUL;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }


}
