package com.yiguohan.easyreading.ViewImpls;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Presenters.DatabasePresenter;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.Util;
import com.yiguohan.easyreading.Views.IGetBookView;
import com.yiguohan.easyreading.Views.IInsertDataView;

import org.w3c.dom.Text;

public class DoubanBookDetailActivity extends BaseActivity implements View.OnClickListener, IGetBookView, IInsertDataView {

    private DoubanBooksPresenter doubanBooksPresenter;

    private DatabasePresenter databasePresenter;

    private ImageView img_Cover;

    private TextView txt_Title;

    private CollapsingToolbarLayout collapsingToolbar;

    private FloatingActionButton btn_register;

    private Book doubanBook;

    private MyBook myBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar_doubanBookDetail);
        img_Cover = (ImageView) findViewById(R.id.img_doubanBookDetail);
        txt_Title = (TextView) findViewById(R.id.txt_doubanBookDetail);
        btn_register = (FloatingActionButton) findViewById(R.id.btn_register);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_doubanBookDetail);

        btn_register.setOnClickListener(this);
        Util.setStatuBarTransparent(this);
        setSupportActionBar(toolbar);
        doubanBooksPresenter = new DoubanBooksPresenter(this);
        Intent intent = getIntent();
        doubanBooksPresenter.getBookById(this, intent.getStringExtra("BookId"));

        databasePresenter = new DatabasePresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("确认")
                        .setMessage("您确定要添加《" + collapsingToolbar.getTitle() + "》吗？")
                        .setCancelable(true)
                        .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myBook = Util.convertBookToMyBook(EasyReadingApplication.getCurrentUserId(), doubanBook);
                                databasePresenter.checkMyBook(DoubanBookDetailActivity.this, myBook);
                            }
                        })
                        .setNegativeButton("再看看", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DoubanBookDetailActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
                break;
        }

    }

    /*-------------------------------------------IGetBookView-------------------------------------------*/
    @Override
    public void getBookSuccess(Book book) {
        doubanBook = book;
        Glide.with(this).
                load(book.getImage()).
                override(300, 300).
                fitCenter().
                into(img_Cover);
        txt_Title.setText(Util.getFormatedBookDetail(book));
        collapsingToolbar.setTitle(book.getTitle());
    }

    @Override
    public void getBookFail() {

    }

     /*-------------------------------------------IInsertDataView-------------------------------------------*/

    @Override
    public void insertDataSuccess(long num) {
        if (num <= 0) {
            insertDataFail();
            return;
        }
        Toast.makeText(this, "添加成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void insertDataFail() {
        Toast.makeText(this, "添加失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void checkData(boolean isSuccess) {
        if (!isSuccess){
            Toast.makeText(this,"已经添加过本书，请在我的阅读中查看",Toast.LENGTH_LONG).show();
            return;
        }
        databasePresenter.insertMyBook(DoubanBookDetailActivity.this, myBook);
    }
}
