/**
 * Feedback Controller
 *
 * @author Peiyuan
 * 2019-04-07
 */

package com.minipgm.message.feedback;

import com.minipgm.util.operationStatus;
import com.minipgm.util.sessionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/list")
    public Object getFeedbackList(HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return feedbackService.getFeedbackList();
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/detail")
    public Object getFeedbackDetail(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return feedbackService.getFeedbackDetail(param.get("feedbackId").toString());
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @PostMapping("/delete")
    public int deleteFeedback(@RequestBody Map<String, Object> param, HttpSession session) {
        try {
            if (sessionCheck.isOnline(session, "ADMIN")) {
                return feedbackService.deleteFeedback(param.get("feedbackId").toString());
            } else {
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

}
