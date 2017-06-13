package com.yiguohan.easyreading.Presenters;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.yiguohan.easyreading.Base.BasePresenter;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.Beans.User;
import com.yiguohan.easyreading.Views.IGetDataView;
import com.yiguohan.easyreading.Views.IGetMyBookListView;
import com.yiguohan.easyreading.Views.IGetMyBookView;
import com.yiguohan.easyreading.Views.IInsertDataView;
import com.yiguohan.easyreading.Views.IUpdateDataView;

import java.util.ArrayList;
import java.util.List;

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


    /*---------------------------------------Users--------------------------------------*/

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
    public void checkAccount(final IInsertDataView view, String account) {
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
        dataBaseService.getUserbyAccount(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        view.getDataSuccess(cursor);
                    }
                });
    }

     /*---------------------------------------MyBooks--------------------------------------*/

    public void insertMyBook(final IInsertDataView view, MyBook book) {
        dataBaseService.insertMyBook(book)
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
     * 检查当前用户是否已经添加过此书
     *
     * @param view
     * @param myBook
     */
    public void checkMyBook(final IInsertDataView view, MyBook myBook) {
        dataBaseService.checkMyBooksExists(myBook)
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

    public void getMyBookListByUserId(final IGetMyBookListView view, String userId) {
        dataBaseService.getMyBooksByUserId(Integer.valueOf(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        if (cursor.getCount() > 0) {
                            List<MyBook> myBooks = new ArrayList<MyBook>();
                            cursor.moveToFirst();
                            do {
                                myBooks.add(parseData(cursor));
                            }while (cursor.moveToNext());
                            view.getMyBookSucess(myBooks);
                        }  else {
                            view.getMyBookFail();
                        }
                    }
                });
    }

    public void getMyBookByMyBookId(final IGetMyBookView view, String myBookId){
        if (TextUtils.isEmpty(myBookId)){
            view.getMybookFail();
            return;
        }
        dataBaseService.getMyBookById(Integer.valueOf(myBookId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        if (cursor.getCount() == 1){
                            cursor.moveToFirst();
                            view.getMyBookSuccess(parseData(cursor));
                        }else {
                            view.getMybookFail();
                        }
                    }
                });
    }

    /**
     * 更新MyBook数据
     * @param view
     * @param myBook
     */
    public void updateMyBook(final IUpdateDataView view, MyBook myBook){
        dataBaseService.updateMyBook(myBook)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        view.updateDataSuceess(integer);
                    }
                });
    }


    /**
     * 解析返回的数据
     * @param cursor
     * @return
     */
    private MyBook parseData(Cursor cursor) {
        MyBook myBook = new MyBook();
        myBook.setId(cursor.getInt(cursor.getColumnIndex("id")));
        myBook.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
        myBook.setBookId(cursor.getInt(cursor.getColumnIndex("bookId")));
        myBook.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        myBook.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
        myBook.setCurrentPage(cursor.getString(cursor.getColumnIndex("currentPage")));
        myBook.setTotalPage(cursor.getString(cursor.getColumnIndex("totalPage")));
        return myBook;
    }

    /*---------------------------------------ReadingRecord--------------------------------------*/

    /**
     * 添加阅读记录
     * @param view
     * @param record
     */
    public void insertReadingRecord(final IInsertDataView view, ReadingRecord record){
        dataBaseService.insertReadingRecord(record)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        view.insertDataSuccess(aLong);
                    }
                });
    }

//    public void checkReadingRecord(final IInsertDataView view,ReadingRecord record,MyBook currentBook){
//        if (record.getCurrentPage())
//    }


}
