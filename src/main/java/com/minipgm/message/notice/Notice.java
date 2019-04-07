/**
 * Notice Class
 *
 * @author Peiyuan
 * 2019-04-03
 */

package com.minipgm.message.notice;

import java.sql.Timestamp;

public class Notice {
    private int userId;
    private String noticeId;
    private String noticeTitle;
    private String noticeContent;
    private int noticeStatus;
    private Timestamp createDate;
    private Timestamp updateDate;

    public Notice() {
        super();
    }

    public Notice(int userId, String noticeId, String noticeTitle, String noticeContent,
                  int noticeStatus, Timestamp createDate, Timestamp updateDate) {
        this.userId = userId;
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeStatus = noticeStatus;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public int getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(int noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
