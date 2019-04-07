/**
 * Notice Controller
 *
 * @author Peiyuan
 * 2019-04-03
 */

package com.minipgm.message.notice;

import com.minipgm.util.idGenerator;
import com.minipgm.util.operationStatus;
import com.minipgm.util.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private idGenerator idGenerator;

    @GetMapping("/current-list")
    public List<NoticeToList> getCurrentList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return noticeService.getCurrentList();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @GetMapping("/old-list")
    public List<NoticeToList> getOldList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return noticeService.getOldList();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/new")
    public Object addNewNotice(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                Notice newNotice = new Notice(Integer.parseInt(session.getAttribute("userId").toString()),
                        idGenerator.noticeId(), param.get("noticeTitle").toString(),
                        param.get("noticeContent").toString(), 0,
                        Timestamp.valueOf("2099-12-31 00:00:00"), Timestamp.valueOf("2099-12-31 00:00:00"));
                Map<String,Object> succeedAdd = new HashMap<String, Object>(){{
                    put("operationStatus",noticeService.addNewNotice(newNotice));
                    put("noticeId",newNotice.getNoticeId());
                }};
                return succeedAdd;
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/upload-picture")
    public Object uploadPicture(@RequestParam("file") MultipartFile picture, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                byte[] bytes = picture.getBytes();
                String pictureSuffix = picture.getOriginalFilename()
                        .substring(picture.getOriginalFilename().lastIndexOf('.'));
                String pictureName = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date())
                        + pictureSuffix;
                return noticeService.uploadPicture(bytes, pictureName);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/modify")
    public int modifyNotice(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                Notice modifyNotice = new Notice(Integer.parseInt(param.get("userId").toString()),
                        param.get("noticeId").toString(), param.get("noticeTitle").toString(),
                        param.get("noticeContent").toString(), 0,
                        Timestamp.valueOf("2099-12-31 00:00:00"), Timestamp.valueOf("2099-12-31 00:00:00"));
                return noticeService.modifyNotice(modifyNotice);
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete")
    public int deleteNotice(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return noticeService.deleteNotice(param.get("noticeId").toString());
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/change-status")
    public int changeNoticeStatus(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return noticeService.changeNoticeStatus(param.get("noticeId").toString(),
                        Integer.parseInt(param.get("status").toString()));
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
