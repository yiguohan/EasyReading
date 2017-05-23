package com.yiguohan.easyreading.Beans;

import android.text.TextUtils;

import com.bumptech.glide.util.Util;

/**
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com *
 */

public class MyBook {
    /**
     * 用户当前正在阅读的书
     * id
     * userId 用户Id
     * bookId 对应豆瓣的Id
     * process 阅读进度
     * title 书名
     * imageUrl 图片Url
     * currentPage 当前阅读的页数
     * totalPage 本书的总页数
     */
    private int id;
    private int userId;
    private int bookId;
    private int process;
    private String rating;
    private String title;
    private String imageUrl;
    private String currentPage;
    private String totalPage;

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

    public long getProcess() {
        if (TextUtils.isEmpty(currentPage)||(TextUtils.isEmpty(totalPage))){
            return 0;
        }
        long current = Long.valueOf(currentPage);
        long total = Long.valueOf(totalPage);
        return current/total;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }
}
