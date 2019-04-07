/**
 * Notice Class
 *
 * @author Peiyuan
 * 2019-04-05
 */

package com.minipgm.message.notice;

import java.sql.Timestamp;

public class NoticeToList{

    private String noticeId;
    private String noticeTitle;
    private Timestamp createDate;
    private Timestamp updateDate;

    public NoticeToList(){
        super();
    }

    public NoticeToList(String noticeId, String noticeTitle, Timestamp createDate, Timestamp updateDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.createDate = createDate;
        this.updateDate = updateDate;
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
