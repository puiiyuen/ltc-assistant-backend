/**
 * Resident Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.resident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService service;

    @GetMapping("/baseInfo")
    public List<ResidentBase> getResBaseInfo(HttpSession session){
        if (session.getAttribute("userId")!=null&&session.getAttribute("userType").toString().equals("ADMIN")){
            return service.getResBaseInfo();
        }
        else {
            return null;
        }
    }

    @PostMapping("/detailInfo")
    public ResidentDetail getResDetailInfo(@RequestBody Map<String, Object> param, HttpSession session){
        if (session.getAttribute("userId")!=null&&session.getAttribute("userType").toString().equals("ADMIN")){
            int resId = Integer.parseInt(param.get("resId").toString());
            return service.getResDetailById(resId);
        }
        else {
            return null;
        }
    }
}
