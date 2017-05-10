package com.yiguohan.easyreading;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.BasePresenter;
import com.yiguohan.easyreading.Beans.Book;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.Views.IGetBookView;

public class MainActivity extends BaseActivity implements IGetBookView {


    private Button button;

    private TextView textView;

    private DoubanBooksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btn_test);
        textView = (TextView)findViewById(R.id.txt_test);
        presenter = new DoubanBooksPresenter(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getBookByISBN(MainActivity.this,"9787508353937");
            }
        });
    }

    @Override
    public void getBookSuccess(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle() + "\n")
                .append(book.getAuthor());
        textView.setText(sb.toString());
    }

    @Override
    public void getBookFail() {

    }
}
