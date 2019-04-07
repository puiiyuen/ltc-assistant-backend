/**
 * Feedback Class
 *
 * @author Peiyuan
 * 2019-04-07
 */

package com.minipgm.message.feedback;

import java.sql.Timestamp;

public class Feedback {

    private int userId;
    private String feedbackId;
    private String feedbackTitle;
    private String feedbackContent;
    private Timestamp createDate;

    public Feedback() {
        super();
    }

    public Feedback(int userId, String feedbackId, String feedbackTitle,
                    String feedbackContent, Timestamp createDate) {
        this.userId = userId;
        this.feedbackId = feedbackId;
        this.feedbackTitle = feedbackTitle;
        this.feedbackContent = feedbackContent;
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
