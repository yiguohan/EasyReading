package com.yiguohan.easyreading.Presenters;

import android.content.Context;
import android.database.Cursor;

import com.yiguohan.easyreading.Base.BasePresenter;
import com.yiguohan.easyreading.Beans.User;
import com.yiguohan.easyreading.Views.IGetDataView;
import com.yiguohan.easyreading.Views.IInsertDataView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiguohan on 2017/5/17.
 * Email:yiguohan@gmail.com
 */

public class DatabasePresenter extends BasePresenter {

    public DatabasePresenter(Context context) {
        super(context);
    }

    /**
     * 添加账户
     *
     * @param view
     * @param user
     */
    public void insertUser(final IInsertDataView view, User user) {
        dataBaseService.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        view.insertDataSuccess(aLong);
                    }
                });
    }

    /**
     * 账户查重
     *
     * @param view
     * @param account
     */
    public void chechAccount(final IInsertDataView view, String account) {
        dataBaseService.checkUserExistbyAccount(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        if (cursor.getCount() > 0) {
                            view.checkData(false);
                        } else {
                            view.checkData(true);
                        }
                    }
                });
    }

    /**
     * 注册时账户密码是否一致
     *
     * @param password
     * @param checkedPassword
     * @return
     */
    public boolean doubleCheckPassword(String password, String checkedPassword) {
        return password.equals(checkedPassword);
    }

    public void login(final IGetDataView view, String account, String password) {
        dataBaseService.getUserbyAccount(account,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        view.getDataSuccess(cursor);
                    }
                });
    }
}
