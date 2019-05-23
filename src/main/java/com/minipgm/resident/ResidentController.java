/**
 * Resident Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.resident;

import com.minipgm.enums.SexEnum;
import com.minipgm.utils.idGenerator;
import com.minipgm.utils.operationStatus;
import com.minipgm.utils.regCodeGenerator;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;
    @Autowired
    private regCodeGenerator regCodeGenerator;
    @Autowired
    private idGenerator idGenerator;

    @GetMapping("/baseInfo")
    public List<ResidentBase> getResBaseInfo(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")) {
                return residentService.getResBaseInfo();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/detailInfo")
    public Object getResDetailInfo(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")) {
                int resId = Integer.parseInt(param.get("resId").toString());
                return residentService.getResDetailById(resId);
            } else {
                return operationStatus.NOT_EXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }

    }

    @PostMapping("/add-resident")
    public int addResident(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                int resId = idGenerator.newId(1);
                int famId = idGenerator.newId(2);
                int resRegcode = regCodeGenerator.newRegCode();
                int famRegcode = regCodeGenerator.newRegCode();
                if (resId == -1 || famId == -1 || resRegcode == -1 || famRegcode == -1) {
                    return operationStatus.FAILED;
                }
                Resident newRes = new Resident(resId, famId, param.get("name").toString(), param.get("goverId").toString(),
                        param.get("phone").toString(), param.get("email").toString(),
                        Integer.parseInt(param.get("bed").toString()), SexEnum.valueOf(param.get("sex").toString()),
                        java.sql.Date.valueOf(param.get("dob").toString()), param.get("address").toString(),
                        param.get("famName").toString(), param.get("famPhone").toString(), param.get("famEmail").toString(),
                        param.get("famAddress").toString(), param.get("medicalHistory").toString(),
                        Date.valueOf(param.get("moveInDate").toString()));
                return residentService.addResident(newRes,resRegcode,famRegcode);
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
            if (sessionCheck.isOnline(session, "ADMIN")) {
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

    @PostMapping("/modify")
    public int modifyResident(@RequestBody Map<String,Object> param,HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                Resident resident= new Resident(Integer.parseInt(param.get("resId").toString()),
                        Integer.parseInt(param.get("famId").toString()),param.get("name").toString(),
                        param.get("goverId").toString(),param.get("phone").toString(),param.get("email").toString(),
                        Integer.parseInt(param.get("bed").toString()),SexEnum.valueOf(param.get("sex").toString()),
                        Date.valueOf(param.get("dob").toString()),param.get("address").toString(),
                         param.get("famName").toString(),param.get("famPhone").toString(),
                        param.get("famEmail").toString(),param.get("famAddress").toString(),
                        param.get("medicalHistory").toString(),Date.valueOf(param.get("moveInDate").toString()));
                return residentService.modifyResident(resident);
            } else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/destroy")
    public Object destroyResident(@RequestBody Map<String,Object> param, HttpSession session){
        try{
            if (sessionCheck.isOnline(session,"ADMIN")){
                return residentService.destroyResident(Integer.parseInt(param.get("resId").toString()),
                        Integer.parseInt(param.get("famId").toString()));
            } else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/search")
    public List<ResidentBase> searchResidents(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session,"ADMIN")) {
                String searchInput = param.get("search").toString();
                if (searchInput.equals("")) {
                    return null;
                }
                int resId, numOfBed;
                String resName;
                if (searchInput.matches("^[0-9]+$")) {
                    resId = Integer.parseInt(searchInput);
                    numOfBed = Integer.parseInt(searchInput);
                    return residentService.searchResidents(resId, "noname", numOfBed);
                } else {
                    resName = searchInput;
                    return residentService.searchResidents(-1, resName, -1);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
