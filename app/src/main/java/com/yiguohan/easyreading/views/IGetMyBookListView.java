package com.yiguohan.easyreading.views;

import com.yiguohan.easyreading.base.IBaseView;
import com.yiguohan.easyreading.beans.MyBook;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/23.
 * Email:yiguohan@gmail.com
 */

public interface IGetMyBookListView extends IBaseView {

    void getMyBookSucess(List<MyBook> myBooks);

    void getMyBookFail();
}
