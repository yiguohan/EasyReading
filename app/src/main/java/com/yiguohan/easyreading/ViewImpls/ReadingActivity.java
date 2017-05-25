package com.yiguohan.easyreading.ViewImpls;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.white.progressview.CircleProgressView;
import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.Util;
import com.yiguohan.easyreading.Views.IGetMyBookView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadingActivity extends BaseActivity implements IGetMyBookView {

    private static final int TIME = 1;

    private Handler handler;

    private DatabasePresenter databasePresenter;

    private EditText edt_CurrentPage;

    private TextView txt_TotalPage;

    @BindView(R.id.txt_bookTitle_reading_activity)
    TextView txt_BookTitle;

    @BindView(R.id.txt_time_reading_activity)
    TextView txt_Time;

    @BindView(R.id.circleprogress_reading_activity)
    CircleProgressView circleProgressView;

    @OnClick(R.id.btn_recordTimeStamp_reading_activity)
    void recordTimeStamp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.setView(R.layout.dialog_recordtimestamp)
                .setTitle("请输入当前页码")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        circleProgressView.setProgress(100);
                        Toast.makeText(EasyReadingApplication.getContext(), edt_CurrentPage.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
        Window window = dialog.getWindow();
        edt_CurrentPage = (EditText) window.findViewById(R.id.edt_currentPage_reading_activity);
        txt_TotalPage = (TextView) window.findViewById(R.id.txt_totalPage_reading_activity);
        txt_TotalPage.setText("Totalpage");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        ButterKnife.bind(this);
        databasePresenter = new DatabasePresenter(this);
        Intent intent = getIntent();
        intent.getStringExtra("MyBookId");
        databasePresenter.getMyBookByMyBookId(this, intent.getStringExtra("MyBookId"));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case TIME:
                        txt_Time.setText(msg.obj.toString());
                }
            }
        };
        getCurrentTime();
    }

    @Override
    public void getMyBookSuccess(MyBook myBook) {
        if (myBook == null){
            getMybookFail();
            return;
        }
        txt_BookTitle.setText(Util.addTitleMark(myBook.getTitle()));
        circleProgressView.setProgress((int)myBook.getProcess());
    }

    @Override
    public void getMybookFail() {
        Toast.makeText(this,"出现未知错误",Toast.LENGTH_SHORT).show();
    }

    /**
     * 开线程获取当前时间
     */
    private void getCurrentTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        String timeString = simpleDateFormat.format(new Date());
                        handler.sendMessage(handler.obtainMessage(TIME, timeString));
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
