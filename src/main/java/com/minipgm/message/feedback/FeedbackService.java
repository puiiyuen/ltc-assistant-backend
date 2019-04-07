/**
 * Feedback Service
 *
 * @author Peiyuan
 * 2019-04-07
 */
package com.minipgm.message.feedback;

import com.minipgm.util.operationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public Object getFeedbackList() {
        try {
            return feedbackMapper.getFeedbackList();
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    public Object getFeedbackDetail(String feedbackId) {
        try {
            return feedbackMapper.getFeedbackDetail(feedbackId);
        } catch (Exception e) {
            e.printStackTrace();
            return operationStatus.SERVERERROR;
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int deleteFeedback(String feedbackId) {
        try {
            if (feedbackMapper.deleteFeedback(feedbackId) == 1) {
                return operationStatus.SUCCESSFUL;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return operationStatus.FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return operationStatus.SERVERERROR;
        }
    }

}
