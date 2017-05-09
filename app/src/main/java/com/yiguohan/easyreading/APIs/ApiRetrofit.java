package com.yiguohan.easyreading.APIs;

import com.yiguohan.easyreading.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yiguohan on 2017/5/9.
 * Email:yiguohan@gmail.com
 */

public class ApiRetrofit {

    public DoubanApi doubanService;
    public static final String DOUBAN_BASE_URL = "https://api.douban.com/";

    public ApiRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOUBAN_BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        doubanService = retrofit.create(DoubanApi.class);//通过create方法传入请求接口来返回一个实现，实现的具体内容Retrofit帮你封装好了，你只需要按照它制定的格式设计接口就行（工厂模式）
    }

    public DoubanApi getDoubanService() {
        return doubanService;
    }
}
