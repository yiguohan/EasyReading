package com.yiguohan.easyreading.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiguohan.easyreading.Beans.DoubanBooks.Book;
import com.yiguohan.easyreading.R;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/11.
 * Email:yiguohan@gmail.com
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> bookList;

    private Context mContext;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;

        ImageView imageView;

        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            imageView = (ImageView)itemView.findViewById(R.id.img_book_item);
            textView = (TextView)itemView.findViewById(R.id.txt_book_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.textView.setText(book.getTitle());
        Glide.with(mContext)
                .load(book.getImage())
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
