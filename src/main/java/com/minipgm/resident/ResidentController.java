/**
 * Resident Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.resident;

import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping("/baseInfo")
    public List<ResidentBase> getResBaseInfo(HttpSession session) {
        try {
            if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {
                return residentService.getResBaseInfo();
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/detailInfo")
    public Object getResDetailInfo(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {

                int resId = Integer.parseInt(param.get("resId").toString());
                return residentService.getResDetailById(resId);
            } else {
                return operationStatus.NOTEXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }

    }

    @PostMapping("/add-resident")
    public int addResident(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (session.getAttribute("userId") != null &&
                    session.getAttribute("userType").toString().equals("ADMIN")) {
                return residentService.addResident(param);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/upload-photo")//upload file identified by government id
    public Object uploadPhoto(@RequestParam("photo") MultipartFile photo,
                              @RequestParam("goverId") String goverId,
                              HttpSession session) {
        try {
            if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {

                byte[] bytes = photo.getBytes();
                String photoSuffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf('.'));
                String photoName = goverId + photoSuffix;
                return residentService.updatePhotoById(bytes, photoName, goverId);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
}
