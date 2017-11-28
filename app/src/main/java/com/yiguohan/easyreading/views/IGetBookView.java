package com.yiguohan.easyreading.views;

import com.yiguohan.easyreading.base.IBaseView;
import com.yiguohan.easyreading.beans.doubanBooks.Book;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public interface IGetBookView extends IBaseView {
    void getBookSuccess(Book book);
    void getBookFail();
}
