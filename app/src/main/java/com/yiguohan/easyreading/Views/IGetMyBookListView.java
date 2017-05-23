package com.yiguohan.easyreading.Views;

import com.yiguohan.easyreading.Base.IBaseView;
import com.yiguohan.easyreading.Beans.MyBook;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/23.
 * Email:yiguohan@gmail.com
 */

public interface IGetMyBookListView extends IBaseView {

    void getMyBookSucess(List<MyBook> myBooks);

    void getMyBookFail();
}
