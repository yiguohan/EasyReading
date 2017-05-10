package com.yiguohan.easyreading.Base;

import android.content.Context;

import com.yiguohan.easyreading.APIs.ApiFactory;
import com.yiguohan.easyreading.APIs.DoubanApi;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public class BasePresenter {

    Context mcontext;

    public BasePresenter(Context context) {
        this.mcontext = context;
    }

    protected DoubanApi doubanService = ApiFactory.getDoubanService();

}
