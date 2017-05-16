package com.yiguohan.easyreading.ViewImpls;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.APIs.ApiFactory;
import com.yiguohan.easyreading.Beans.User;
import com.yiguohan.easyreading.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private Button btn_insert;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_insert = (Button) view.findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_query = (Button) view.findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);
        textView = (TextView) view.findViewById(R.id.txt_test);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                User user = new User();
                user.setAccount("yiguohan");
                user.setPassword("19920207");
                ApiFactory.getDbService(getContext()).insertUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
                                Toast.makeText(getContext(), "目前最大序号为" + aLong, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_delete:
                ApiFactory.getDbService(getContext()).deleteUser(11)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Toast.makeText(getContext(), "已删除行数： " + integer, Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_update:
                User user1 = new User();
                user1.setId(12);
                user1.setAccount("yiguohan");
                user1.setPassword("19920207");
                ApiFactory.getDbService(getContext()).updateUser(user1)
                        .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            Toast.makeText(getContext(), "已修改行数： " + integer, Toast.LENGTH_SHORT).show();
                        }
                    });
                break;
            case R.id.btn_query:
                ApiFactory.getDbService(getContext()).getUserbyAccount("yiguohan","19920207")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Cursor>() {
                            @Override
                            public void accept(Cursor cursor) throws Exception {
                                StringBuilder sb = new StringBuilder();
                                if (cursor.moveToFirst()){
                                    do {
                                        sb.append(cursor.getString(cursor.getColumnIndex("id")));
                                        sb.append(" ");
                                        sb.append(cursor.getString(cursor.getColumnIndex("account")));
                                        sb.append(" ");
                                        sb.append(cursor.getString(cursor.getColumnIndex("password")));
                                        sb.append("\n");
                                    }while (cursor.moveToNext());

                                }
                                cursor.close();
                                textView.setText(sb.toString());
                            }
                        });
                break;
        }
    }
}
