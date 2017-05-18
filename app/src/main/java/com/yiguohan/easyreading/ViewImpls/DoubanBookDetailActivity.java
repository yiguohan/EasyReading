package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Views.IGetBookView;

public class DoubanBookDetailActivity extends AppCompatActivity implements IGetBookView{

    private DoubanBooksPresenter presenter;

//    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_book_detail);
//        textView = (TextView)findViewById(R.id.txt_BookDetail);
        presenter = new DoubanBooksPresenter(this);
        Intent intent = getIntent();
        presenter.getBookById(this,intent.getStringExtra("BookId"));
    }

    @Override
    public void getBookSuccess(Book book) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(book.getTitle() + "\n")
//                .append(book.getAuthor()+"\n");
//        textView.setText(sb.toString());
    }

    @Override
    public void getBookFail() {

    }
}
