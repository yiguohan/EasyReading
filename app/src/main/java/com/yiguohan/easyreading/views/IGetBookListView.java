package com.yiguohan.easyreading.views;

import com.yiguohan.easyreading.base.IBaseView;
import com.yiguohan.easyreading.beans.doubanBooks.BookList;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public interface IGetBookListView extends IBaseView {
    void getBookListSuccess(BookList list, boolean isMore);
}
