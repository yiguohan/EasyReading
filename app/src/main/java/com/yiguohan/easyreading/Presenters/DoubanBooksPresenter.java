package com.yiguohan.easyreading.Presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.yiguohan.easyreading.Base.BasePresenter;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.DoubanBooks.BookList;
import com.yiguohan.easyreading.Views.IGetBookListView;
import com.yiguohan.easyreading.Views.IGetBookView;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                        displayBookDetail(view, book);
                    }
                });

    }

    public void getBooksByTag(final IGetBookListView view, String tag) {
        doubanService.getBookByTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookList>() {
                    @Override
                    public void accept(@NonNull BookList list) throws Exception {
                        displayBookList(view, list);
                    }
                });

    }

    public void getBooksByKeyWord(final IGetBookListView view, String keyword) {

        doubanService.getBookByKeyword(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookList>() {
                    @Override
                    public void accept(@NonNull BookList list) throws Exception {
                        displayBookList(view, list);
                    }
                });
    }

    public void getBookById(final IGetBookView view, String id) {
        doubanService.getBookById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) throws Exception {
                        displayBookDetail(view, book);
                    }
                });
    }

    private void displayBookDetail(IGetBookView view, Book b) {
        view.getBookSuccess(b);
    }

    private void displayBookList(IGetBookListView view, BookList list) {
        view.getBookListSuccess(list, false);
    }

    private void displayError(Throwable throwable) {
        throwable.printStackTrace();

    }
}
