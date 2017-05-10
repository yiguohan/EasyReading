package com.yiguohan.easyreading.Views;

import com.yiguohan.easyreading.Base.IBaseView;
import com.yiguohan.easyreading.Beans.Book;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public interface IGetBookView extends IBaseView {
    void getBookSuccess(Book book);
    void getBookFail();
}
