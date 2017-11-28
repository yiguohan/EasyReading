package com.yiguohan.easyreading.views;

import com.yiguohan.easyreading.base.IBaseView;

/**
 * Created by yiguohan on 2017/5/22.
 * Email:yiguohan@gmail.com
 */

public interface IInsertDataView extends IBaseView{
    void insertDataSuccess(long num);
    void insertDataFail();
    void checkData(boolean isSuccess);
}
