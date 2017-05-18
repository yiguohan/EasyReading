package com.yiguohan.easyreading.ViewImpls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


}

