/**
 * Resident Controller
 *
 * @author Peiyuan
 * 2019-01-21
 */

package com.minipgm.resident;

import com.minipgm.user.UserService;
import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;
    private UserService userService;
    private String UPLOAD_FOLDER = "/opt/photo/";
    private String photoName = "noname";

    @GetMapping("/baseInfo")
    public List<ResidentBase> getResBaseInfo(HttpSession session) {
        if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {
            return residentService.getResBaseInfo();
        } else {
            return null;
        }
    }

    @PostMapping("/detailInfo")
    public Object getResDetailInfo(@RequestBody Map<String, Object> param, HttpSession session) {
        if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {
            try {
                int resId = Integer.parseInt(param.get("resId").toString());
                return residentService.getResDetailById(resId);
            } catch (Exception e) {
                e.printStackTrace();
                return operationStatus.SERVERERROR;
            }
        } else {
            return operationStatus.NOTEXIST;
        }
    }

    @PostMapping("/add-resident")
    public int addResident(@RequestBody Map<String, Object> param, HttpSession session) {
        if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {
            try {
                residentService.addResident(param);
                return operationStatus.SUCCESSFUL;
            } catch (Exception e) {
                e.printStackTrace();
                return operationStatus.SERVERERROR;
            }
        } else {
            return operationStatus.FAILED;
        }
    }

    @PostMapping("/upload-photo")
    public Object uploadPhoto(MultipartFile photo, HttpSession session) {
        if (session.getAttribute("userId") != null && session.getAttribute("userType").toString().equals("ADMIN")) {
            try {
                byte[] bytes = photo.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + photo.getOriginalFilename());
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                Files.write(path, bytes);
                photoName = photo.getOriginalFilename();
                return operationStatus.SUCCESSFUL;
            } catch (IOException e) {
                e.printStackTrace();
                return operationStatus.SERVERERROR;
            }
        } else {
            return operationStatus.FAILED;
        }
    }
}
