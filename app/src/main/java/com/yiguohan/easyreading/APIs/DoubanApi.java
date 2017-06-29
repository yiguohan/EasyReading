package com.yiguohan.easyreading.APIs;


import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.DoubanBooks.BookList;

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
     * 通过图书Id来检索图书信息
     *
     * @param id
     * @return
     */
    @GET("v2/book/{id}")
    Observable<Book> getBookById(@Path("id") String id);

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
     * @param tag   搜索的标签
     * @param start 开始的数目（用于进行分页）
     * @return
     */
    @GET("v2/book/search")
    Observable<BookList> getBookByTag(@Query("tag") String tag, @Query("start") int start);

    /**
     * 通过关键字查询图书信息
     *
     * @param keyword
     * @return
     */
    @GET("v2/book/search")
    Observable<BookList> getBookByKeyword(@Query("q") String keyword);
}
