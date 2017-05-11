package com.yiguohan.easyreading.Beans.DoubanBooks;

import java.util.List;

/**
 * Created by yiguo on 2017/5/4.
 * Email:yiguohan@gmail.com
 */

public class Book {

    /**
     * id : 豆瓣图书ID
     * title : 书名
     * author : 作者List
     * publisher : 出版社
     * pages : 页码
     * pubdate : 出版时间
     * image : 图片Url
     * alt :豆瓣介绍页面url
     * isbn13 : isbn号
     * price : 价格
     */

    private String id;
    private String title;
    private String publisher;
    private String pages;
    private String pubdate;
    private String image;
    private String alt;
    private String isbn13;
    private String price;
    private List<String> author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }
}
