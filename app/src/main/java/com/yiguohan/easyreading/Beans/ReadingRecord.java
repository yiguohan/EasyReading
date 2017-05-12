package com.yiguohan.easyreading.Beans;

import java.util.Date;

/**
 * Created by yiguo on 2017/5/8.
 * Email:yiguohan@gmail.com
 */

public class ReadingRecord {
    /**
     * 阅读记录
     * id 阅读记录的id
     * userId 用户表的id
     * bookId Book表的id
     * currentPage 当前阅读的页数
     * timeStamp 时间戳
     */
    private int id;
    private int userId;
    private int bookId;
    private int currentPage;
    private Date timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
