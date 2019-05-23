package com.minipgm.memo;

import com.minipgm.utils.operationStatus;
import com.minipgm.utils.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    private MemoService memoService;

    @GetMapping("/list")
    public Object memoList(HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"RESIDENT")){
                int userId = (int)session.getAttribute("userId");
                return memoService.memoList(userId);
            } else {
                return operationStatus.FAILED;
            }
        }catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/detail")
    public Object memoDetail(@RequestBody Map<String,Object> param, HttpSession session){
        try {
            if (sessionCheck.isOnline(session,"RESIDENT")){
                return memoService.memoDetail((int)session.getAttribute("userId"),(int)param.get("memoId"));
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e){
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
