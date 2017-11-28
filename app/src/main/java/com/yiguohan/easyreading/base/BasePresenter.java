package com.yiguohan.easyreading.base;

import android.content.Context;

import com.yiguohan.easyreading.api.ApiFactory;
import com.yiguohan.easyreading.api.DBApi;
import com.yiguohan.easyreading.api.DoubanApi;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public class BasePresenter {

    private Context mContext;
    protected DoubanApi doubanService;
    protected DBApi dataBaseService;

    public BasePresenter(Context context) {
        this.mContext = context;
        doubanService = ApiFactory.getDoubanService();
        dataBaseService = ApiFactory.getDbService(mContext);
    }
}
