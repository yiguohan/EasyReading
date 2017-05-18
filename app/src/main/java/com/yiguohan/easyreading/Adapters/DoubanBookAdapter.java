package com.yiguohan.easyreading.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    List<Book> bookList;
    Context mContext;

    public DoubanBookAdapter(List<Book> books) {
        this.bookList = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (mContext == null){
           mContext = parent.getContext();
       }
       View view = LayoutInflater.from(mContext).inflate(R.layout.douban_book_item,null);
        final ViewHolder holder = new ViewHolder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = bookList.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book.getAlt()));
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       Book book = bookList.get(position);
        Glide.with(mContext).load(book.getImage()).fitCenter().into(holder.imageView);
        holder.txt_rating.setText("评分：10.0");
        holder.txt_title.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txt_title;
        TextView txt_rating;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img_DoubanBook_item);
            txt_title = (TextView)itemView.findViewById(R.id.txt_DoubanBook_title);
            txt_rating = (TextView)itemView.findViewById(R.id.txt_DoubanBook_Rating);
        }
    }
}
