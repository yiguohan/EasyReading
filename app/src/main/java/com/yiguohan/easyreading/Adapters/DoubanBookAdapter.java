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
 * Created by yiguohan on 2017/5/17.
 * Email:yiguohan@gmail.com
 */

public class DoubanBookAdapter extends RecyclerView.Adapter<DoubanBookAdapter.ViewHolder> {

    List<Book> books;
    Context mContext;

    public DoubanBookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (mContext == null){
           mContext = parent.getContext();
       }
       View view = LayoutInflater.from(mContext).inflate(R.layout.douban_book_item,parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       Book book = books.get(position);
        Glide.with(mContext).load(book.getImage()).fitCenter().into(holder.imageView);
        holder.txt_rating.setText("评分：10.0");
        holder.txt_title.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView txt_title;
        TextView txt_rating;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView_DoubanBook);
            imageView = (ImageView)itemView.findViewById(R.id.img_DoubanBook_item);
            txt_title = (TextView)itemView.findViewById(R.id.txt_DoubanBook_title);
            txt_rating = (TextView)itemView.findViewById(R.id.txt_DoubanBook_Rating);
        }
    }
}
