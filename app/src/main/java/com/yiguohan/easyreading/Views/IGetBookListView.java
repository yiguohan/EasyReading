package com.yiguohan.easyreading.Views;

import com.yiguohan.easyreading.Base.IBaseView;
import com.yiguohan.easyreading.Beans.Book;
import com.yiguohan.easyreading.Beans.BookList;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public interface IGetBookListView extends IBaseView {
    void getBookListSuccess(BookList list, boolean isMore);
}
