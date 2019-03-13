/**
 * Health Controller
 *
 * @author Peiyuan
 * 2019-03-10
 */

package com.minipgm.health;

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
            if (session.getAttribute("userId") != null &&
                    session.getAttribute("userType").toString().equals("ADMIN")) {
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
            if (session.getAttribute("userId") != null &&
                    session.getAttribute("userType").toString().equals("ADMIN")) {
                return healthService.getReportById(Integer.parseInt(param.get("resId").toString()));
            }else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
