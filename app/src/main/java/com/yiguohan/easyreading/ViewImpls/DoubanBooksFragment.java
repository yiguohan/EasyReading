package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yiguohan.easyreading.Adapters.DoubanBookAdapter;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.Beans.DoubanBooks.BookList;
import com.yiguohan.easyreading.Listeners.EndlessOnScrollListener;
import com.yiguohan.easyreading.Presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Views.IGetBookListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoubanBooksFragment extends BaseFragment implements IGetBookListView {

    List<Book> bookList = new ArrayList<Book>();

    DoubanBookAdapter adapter;

    private RecyclerView recyclerView;

    private String bookTag;

    private int currentItemsCount = 0;//记录已经返回的数据个数

    public DoubanBooksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_douban_books, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.bookTag = this.getArguments().getString("TAG", "Design");

        recyclerView = (RecyclerView) view.findViewById(R.id.douban_recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new DoubanBookAdapter(bookList);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new EndlessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                initData(currentItemsCount);
            }
        });
    }

    @Override
    public void getBookListSuccess(BookList list, boolean isMore) {
        for (Book b :
                list.getBooks()) {
            bookList.add(b);
        }
        currentItemsCount += 20;
        adapter.notifyDataSetChanged();
    }

    private void initData() {
        initData(0);
    }

    private void initData(int currentItemsCount) {
        DoubanBooksPresenter doubanBooksPresenter = new DoubanBooksPresenter(getContext());
        doubanBooksPresenter.getBooksByTag(this, bookTag, currentItemsCount);
    }

    @Override
    public void isOnLine() {
        super.isOnLine();
        initData();
    }

    @Override
    public void isOffLine() {
        super.isOffLine();
    }
}
