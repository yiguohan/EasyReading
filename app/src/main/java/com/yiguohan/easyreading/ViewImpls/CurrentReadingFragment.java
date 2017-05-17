package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.Adapters.BookAdapter;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.DoubanBooks.BookList;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Views.IGetBookListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的阅读
 */
public class CurrentReadingFragment extends BaseFragment implements IGetBookListView{

    private static final String TAG = "CurrentReadingFragment";

    List<Book> books = new ArrayList<Book>();

    BookAdapter adapter;

    DoubanBooksPresenter presenter;

    public CurrentReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_current_reading, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.currentReading_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter(books);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void getBookListSuccess(BookList list, boolean isMore) {
        books.clear();
        for (Book b : list.getBooks()) {
            books.add(b);
        }
        initView();
    }

    private void initData(){
        presenter = new DoubanBooksPresenter(getContext());
        presenter.getBooksByKeyWord(CurrentReadingFragment.this,"Head First");
    }

    private void initView(){
        adapter.notifyDataSetChanged();
    }

}
