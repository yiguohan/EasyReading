package com.yiguohan.easyreading.Views;

import com.yiguohan.easyreading.Base.IBaseView;

/**
 * Created by yiguohan on 2017/5/22.
 * Email:yiguohan@gmail.com
 */

public interface IUpdateDataView extends IBaseView{
    void updateDataSuceess(int num);
    void updateDataFail();
}
