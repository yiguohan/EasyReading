package com.yiguohan.easyreading.views;

import com.yiguohan.easyreading.base.IBaseView;
import com.yiguohan.easyreading.beans.MyBook;

/**
 * Created by yiguohan on 2017/5/25.
 * Email:yiguohan@gmail.com
 */

public interface IGetMyBookView extends IBaseView {
    void getMyBookSuccess(MyBook myBook);
    void getMybookFail();
}
