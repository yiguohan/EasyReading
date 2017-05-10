package com.yiguohan.easyreading.APIs;

import com.yiguohan.easyreading.Beans.Book;
import com.yiguohan.easyreading.Beans.BookList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * HTTP请求的接口
 * Created by yiguohan on 2017/5/9.
 * Email:yiguohan@gmail.com
 */

public interface DoubanApi {

    /**
     * 通过ISBN号来查询图书信息
     * 用于扫描二维码后获取ISBN号后调用
     *
     * @param isbn 图书的ISBN号
     * @return
     */
    @GET("v2/book/isbn/:{isbn}")
    Observable<Book> getBookByIBSN(@Path("isbn") String isbn);

    /**
     * 通过图书标签来查询图书信息
     * 用于用户输入指定标签查询
     *
     * @param tag
     * @return
     */
    @GET("v2/book/search")
    Observable<BookList> getBookByTag(@Query("tag") String tag);

    /**
     * 通过关键字查询图书信息
     *
     * @param keyword
     * @return
     */
    @GET("v2/book/search")
    Observable<BookList> getBookByKeyword(@Query("q") String keyword);
}
