/**
 * Location Controller
 *
 * @author Peiyuan
 * 2019-03-26
 */

package com.minipgm.location;

import com.minipgm.util.operationStatus;
import com.minipgm.util.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/geofence")// add session check later
    public int geoFence(@RequestBody List<Map<String,Object>> param){
        System.out.println(param);
        for (Map item:param){
            System.out.println(item);
        }
        return operationStatus.SUCCESSFUL;
    }

    @GetMapping("/geofence")
    public List<Location> getUserLocation(HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                return new ArrayList<Location>();
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
