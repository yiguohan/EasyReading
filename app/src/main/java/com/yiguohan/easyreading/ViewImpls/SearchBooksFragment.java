package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
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
public class SearchBooksFragment extends Fragment implements IGetBookListView{

    private FloatingSearchView mSearchView;

    private DoubanBooksPresenter presenter;

    private DoubanBookAdapter adapter;

    private List<Book> books = new ArrayList<Book>();

    private RecyclerView recyclerView;


    public SearchBooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_books, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new DoubanBookAdapter(books);
        recyclerView = (RecyclerView)view.findViewById(R.id.searchBooks_recyclerView);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new DoubanBooksPresenter(getContext());
        mSearchView = (FloatingSearchView)view.findViewById(R.id.searchBooks_FloatingSearchView);
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                presenter.getBooksByKeyWord(SearchBooksFragment.this,searchSuggestion.getBody());
            }

            @Override
            public void onSearchAction(String currentQuery) {
                presenter.getBooksByKeyWord(SearchBooksFragment.this,currentQuery);
            }
        });
    }

    @Override
    public void getBookListSuccess(BookList list, boolean isMore) {
        books.clear();
        for (Book b :
                list.getBooks()) {
            books.add(b);
        }
        adapter.notifyDataSetChanged();
    }
}
