package com.yiguohan.easyreading.viewImpls.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.yiguohan.easyreading.adapters.DoubanBookAdapter;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.beans.doubanBooks.Book;
import com.yiguohan.easyreading.beans.doubanBooks.BookList;
import com.yiguohan.easyreading.listeners.EndlessOnScrollListener;
import com.yiguohan.easyreading.presenters.DoubanBooksPresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.views.IGetBookListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBooksFragment extends BaseFragment implements IGetBookListView {

    private FloatingSearchView mSearchView;

    private DoubanBooksPresenter presenter;

    private DoubanBookAdapter adapter;

    private List<Book> books = new ArrayList<Book>();

    private RecyclerView recyclerView;

    private String keyword;

    private int currentItemsCount = 0;

    public SearchBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_books, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new DoubanBookAdapter(books);

        presenter = new DoubanBooksPresenter(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.searchBooks_recyclerView);

        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new EndlessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                if (TextUtils.isEmpty(keyword)) {

                    Toast.makeText(getContext(), "请输入关键词", Toast.LENGTH_SHORT).show();

                    return;
                }

                initData(keyword, currentItemsCount);

            }
        });

        mSearchView = (FloatingSearchView) view.findViewById(R.id.searchBooks_FloatingSearchView);
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                keyword = searchSuggestion.getBody();

                initData(keyword);
            }

            @Override
            public void onSearchAction(String currentQuery) {

                keyword = currentQuery;

                initData(keyword);
            }
        });
    }

    private void initData(String keyword, int start) {

        presenter.getBooksByKeyWord(SearchBooksFragment.this, keyword, start);

        currentItemsCount += 20;
    }

    private void initData(String keyword) {
        initData(keyword, 0);
    }

    @Override
    public void getBookListSuccess(BookList list, boolean isMore) {
//        books.clear();
        for (Book b :
                list.getBooks()) {
            books.add(b);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        books.clear();
    }
}
