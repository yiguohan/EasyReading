package com.yiguohan.easyreading.Views;

import com.yiguohan.easyreading.Base.IBaseView;
import com.yiguohan.easyreading.Beans.MyBook;

/**
 * Created by yiguohan on 2017/5/25.
 * Email:yiguohan@gmail.com
 */

public interface IGetMyBookView extends IBaseView {
    void getMyBookSuccess(MyBook myBook);
    void getMybookFail();
}
