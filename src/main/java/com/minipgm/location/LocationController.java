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

    @PostMapping("/list")// add session check later
    public int geoFence(@RequestBody List<Map<String,Object>> param){
        List<Location> locationList = new ArrayList<>();
        for (Map item:param){
            Location location = new Location(Integer.parseInt(item.get("userId").toString()),
                    Double.parseDouble(item.get("longitude").toString()),
                    Double.parseDouble(item.get("latitude").toString()),
                    item.get("timestamp").toString());
            locationList.add(location);
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
