package com.yiguohan.easyreading.Presenters;


import android.content.Context;

import com.yiguohan.easyreading.Base.BasePresenter;
import com.yiguohan.easyreading.Beans.Book;
import com.yiguohan.easyreading.Views.IGetBookView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yiguohan on 2017/5/10.
 * Email:yiguohan@gmail.com
 */

public class DoubanBooksPresenter extends BasePresenter {

    public DoubanBooksPresenter(Context context) {
        super(context);
    }

    public void getBookByISBN(final IGetBookView view, String isbn) {
        doubanService.getBookByIBSN(isbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Book>() {
                    @Override
                    public void accept(@NonNull Book book) throws Exception {
                        displayBookDetail(view,book);
                    }
                });

    }

    public void getBooksByTag() {

    }

    public void getBooksByKeyWord() {

    }

    private void displayBookDetail(IGetBookView view, Book b) {
        view.getBookSuccess(b);
    }

    private void displayError(Throwable throwable){
        throwable.printStackTrace();

    }
}
