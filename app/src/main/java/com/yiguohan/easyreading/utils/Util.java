package com.yiguohan.easyreading.utils;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yiguohan.easyreading.base.BaseActivity;
import com.yiguohan.easyreading.beans.doubanBooks.Book;
import com.yiguohan.easyreading.beans.MyBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yiguohan on 2017/5/18.
 * Email:yiguohan@gmail.com
 */

public class Util {

    public static List<String> getBookTags() {
        List<String> list = new ArrayList<String>();
        list.add("综合");
        list.add("文学");
        list.add("生活");
        list.add("技术");
        return list;

    }

    public static String getFormatedBookDetail(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle())
                .append("\n")
                .append("豆瓣评分:" + book.getRating().getAverage())
                .append("\n")
                .append(book.getAuthor())
                .append("\n")
                .append("\n")
                .append(book.getPublisher())
                .append("\n")
                .append("出版日期：" + book.getPubdate())
                .append("\n")
                .append(book.getRating().getNumRaters() + "人评价过")
                .append("\n")
                .append("\n")
                .append("\n")
                .append("简介")
                .append("\n")
                .append(book.getSummary())
                .append("\n")
                .append("\n")
                .append("\n")
                .append("目录")
                .append("\n")
                .append(book.getCatalog());

        return sb.toString();
    }

    public static String getFormatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH时mm分");
        return dateFormat.format(date);
    }

    /**
     * 设置状态栏为透明
     *
     * @param activity
     */
    public static void setStatuBarTransparent(BaseActivity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 在添加阅读书籍时记录阅读书籍的类型转换
     *
     * @param userId     记录书籍的用户Id
     * @param doubanBook 豆瓣的书籍信息
     * @return
     */
    public static MyBook convertBookToMyBook(String userId, Book doubanBook) {
        MyBook myBook = new MyBook();
        myBook.setBookId(Integer.valueOf(doubanBook.getId()));
        myBook.setUserId(Integer.valueOf(userId));
        myBook.setTitle(doubanBook.getTitle());
        myBook.setCurrentPage("0");//添加时页码均为0
        myBook.setTotalPage(doubanBook.getPages());
        myBook.setImageUrl(doubanBook.getImage());
        myBook.setRating(doubanBook.getRating().getAverage());
        return myBook;
    }

    public static String addTitleMark(String bookTitle) {
        StringBuilder sb = new StringBuilder();
        sb.append("《")
                .append(bookTitle)
                .append("》");
        return sb.toString();
    }

    public static void setHelloSlogan(TextView textView, String slogan) {
        if (TextUtils.isEmpty(slogan)) {
            textView.setText("欢迎你！");
            return;
        }
        textView.setText("欢迎你，" + slogan + "！");
    }

    public static void setHelloSlogan(TextView textView) {
        setHelloSlogan(textView, "");
    }
}
