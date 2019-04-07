/**
 * FeedbackToList Class
 *
 * @author Peiyuan
 * 2019-04-07
 */

package com.minipgm.message.feedback;

import java.sql.Timestamp;

public class FeedbackToList {

    private String feedbackId;
    private String feedbackTitle;
    private Timestamp createDate;

    public FeedbackToList() {
        super();
    }

    public FeedbackToList(Feedback feedback) {
        this.feedbackTitle = feedback.getFeedbackTitle();
        this.createDate = feedback.getCreateDate();
        this.feedbackId = feedback.getFeedbackId();
    }

    public FeedbackToList(String feedbackId, String feedbackTitle, Timestamp createDate) {
        this.feedbackId = feedbackId;
        this.feedbackTitle = feedbackTitle;
        this.createDate = createDate;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
}
