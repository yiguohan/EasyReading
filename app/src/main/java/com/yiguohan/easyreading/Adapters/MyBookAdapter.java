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
import com.white.progressview.CircleProgressView;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.R;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/23.
 * Email:yiguohan@gmail.com
 */

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.ViewHolder> {

    List<MyBook> myBooks;

    Context mContext;

    public MyBookAdapter(List<MyBook> myBooks) {
        this.myBooks = myBooks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_book_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyBook myBook = myBooks.get(position);
        Glide.with(mContext).load(myBook.getImageUrl()).fitCenter().into(holder.img_Cover);
        holder.txt_Title.setText(myBook.getTitle());
//        holder.txt_Rating.setText("豆瓣评分：" + myBook.getRating());
        holder.txt_Pages.setText(myBook.getCurrentPage() +"/"+myBook.getTotalPage());
        holder.circleImageView_progress.setProgress((int)myBook.getProcess());
    }

    @Override
    public int getItemCount() {
        return myBooks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView img_Cover;
        TextView txt_Title;
        TextView txt_Pages;
        CircleProgressView circleImageView_progress;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            img_Cover = (ImageView) itemView.findViewById(R.id.img_book_item);
            txt_Title = (TextView) itemView.findViewById(R.id.txt_book_item);
            txt_Pages = (TextView) itemView.findViewById(R.id.txt_pages_book_item);
            circleImageView_progress = (CircleProgressView) itemView.findViewById(R.id.progressView_book_item);
        }
    }
}
