package com.yiguohan.easyreading.ViewImpls;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.APIs.ApiFactory;
import com.yiguohan.easyreading.APIs.DBApi;
import com.yiguohan.easyreading.APIs.DoubanApi;
import com.yiguohan.easyreading.Adapters.DoubanBookAdapter;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.DoubanBooks.BookList;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Views.IGetBookListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IGetBookListView{

    List<Book> bookList = new ArrayList<Book>();

    DoubanBookAdapter adapter;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.home_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DoubanBookAdapter(bookList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void getBookListSuccess(BookList list, boolean isMore) {
        bookList.clear();
        for (Book b :
                list.getBooks()) {
            bookList.add(b);
        }
        adapter.notifyDataSetChanged();
    }

    private void initData(){
        DoubanBooksPresenter doubanBooksPresenter = new DoubanBooksPresenter(getContext());
        doubanBooksPresenter.getBooksByTag(this,"Design");
    }
}

