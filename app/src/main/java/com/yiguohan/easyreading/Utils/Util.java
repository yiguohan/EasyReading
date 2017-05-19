package com.yiguohan.easyreading.Utils;

import com.yiguohan.easyreading.Beans.DoubanBooks.Book;

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

    public static String getFormatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH时mm分");
        return dateFormat.format(date);
    }
}
