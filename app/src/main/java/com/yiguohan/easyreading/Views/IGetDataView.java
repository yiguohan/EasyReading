package com.yiguohan.easyreading.Views;

import android.database.Cursor;

import com.yiguohan.easyreading.Base.IBaseView;

/**
 * Created by yiguohan on 2017/5/22.
 * Email:yiguohan@gmail.com
 */

public interface IGetDataView extends IBaseView {
    void getDataSuccess(Cursor cursor);
    void getDataFail();
}
