package com.minipgm.memo;

import java.sql.Timestamp;

public class Memo {
    private int userId;
    private String memoId;
    private String memoTitle;
    private String memoContent;
    private Timestamp recordTime;

    public Memo(){
        super();
    }

    public Memo(int userId, String memoId, String memoTitle,
                String memoContent, Timestamp recordTime) {
        this.userId = userId;
        this.memoId = memoId;
        this.memoTitle = memoTitle;
        this.memoContent = memoContent;
        this.recordTime = recordTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMemoId() {
        return memoId;
    }

    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }
}
