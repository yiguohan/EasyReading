package com.yiguohan.easyreading.viewImpls.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.adapters.MyBookAdapter;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.base.EasyReadingApplication;
import com.yiguohan.easyreading.beans.MyBook;
import com.yiguohan.easyreading.presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.views.IGetMyBookListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的阅读
 */
public class CurrentReadingFragment extends BaseFragment implements IGetMyBookListView {

    private static final String TAG = "CurrentReadingFragment";

    List<MyBook> myBooks = new ArrayList<MyBook>();

    MyBookAdapter adapter;

    DatabasePresenter presenter;

    public CurrentReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.currentReading_recyclerview);
        adapter = new MyBookAdapter(myBooks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void getMyBookSucess(List<MyBook> books) {
        this.myBooks.clear();
        for (MyBook myBook : books) {
            this.myBooks.add(myBook);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getMyBookFail() {

    }

    private void initData() {
        MyBook myBook = new MyBook();
        myBook.setTitle("text");
        myBooks.add(myBook);
        presenter = new DatabasePresenter(getContext());
        presenter.getMyBookListByUserId(this, EasyReadingApplication.getCurrentUserId());
    }


}
