/**
 * Staff Controller
 *
 * @author Peiyuan
 * 2019-04-08
 */

package com.minipgm.staff;

import com.minipgm.enums.SexEnum;
import com.minipgm.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private idGenerator idGenerator;
    @Autowired
    private regCodeGenerator regCodeGenerator;

    @GetMapping("/list")
    public Object getStaffList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return staffService.getStaffList();
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/detail")
    public Object getStaffDetail(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return staffService.getStaffDetail(Integer.parseInt(param.get("staffId").toString()));
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/add")
    public int addStaff(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                int staffId = idGenerator.newId(0);
                int staffRegcode = regCodeGenerator.newRegCode();

                if (staffId == -1 || staffRegcode == -1) {
                    return operationStatus.FAILED;
                }

                Staff newStaff = new Staff(staffId, param.get("name").toString(), param.get("goverId").toString(),
                        param.get("phone").toString(), param.get("email").toString(),
                        SexEnum.valueOf(param.get("sex").toString()), Date.valueOf(param.get("dob").toString()),
                        param.get("address").toString(), param.get("photoUrl").toString(),
                        Date.valueOf(param.get("moveInDate").toString()));
                return staffService.addStaff(newStaff, staffRegcode);

            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/upload-photo")
    public Object uploadPhoto(@RequestParam("photo") MultipartFile photo,
                              HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                byte[] bytes = photo.getBytes();
                String photoSuffix = photo.getOriginalFilename()
                        .substring(photo.getOriginalFilename().lastIndexOf('.'));
                String photoName = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new java.util.Date())
                        + photoSuffix;
                return staffService.uploadPhoto(bytes, photoName);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/modify")
    public int modifyStaff(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                Staff staff = new Staff(Integer.parseInt(param.get("staffId").toString()), param.get("name").toString(),
                        param.get("goverId").toString(), param.get("phone").toString(), param.get("email").toString(),
                        SexEnum.valueOf(param.get("sex").toString()), Date.valueOf(param.get("dob").toString()),
                        param.get("address").toString(), param.get("photoUrl").toString(),
                        Date.valueOf(param.get("moveInDate").toString()));
                return staffService.modifyStaff(staff);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete")
    public int deleteStaff(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return staffService.deleteStaff(Integer.parseInt(param.get("staffId").toString()));
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/search")
    public Object searchStaffs(@RequestBody Map<String,Object> param, HttpSession session ){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                String searchInput = param.get("search").toString();
                if (searchInput.equals("")){
                    return null;
                }
                int staffId;
                if (searchInput.matches("^[0-9]+$")){
                    staffId = Integer.parseInt(searchInput);
                    return staffService.searchStaffs(staffId,"noname");
                } else {
                    return staffService.searchStaffs(-1,searchInput);
                }
            } else {
                return operationStatus.FAILED;
            }
        }  catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }
    
    @PostMapping("/attendance")
    public int staffAttendance(@RequestBody Map<String,Object> param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"ADMIN")){
                int staffId = (int) param.get("staffId");
                int status = (int) param.get("status");
                return staffService.staffAttendance(staffId,status);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
