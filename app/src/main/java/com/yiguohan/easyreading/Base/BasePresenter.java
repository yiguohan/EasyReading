package com.yiguohan.easyreading.Base;

import android.content.Context;

import com.yiguohan.easyreading.APIs.ApiFactory;
import com.yiguohan.easyreading.APIs.DBApi;
import com.yiguohan.easyreading.APIs.DoubanApi;

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
