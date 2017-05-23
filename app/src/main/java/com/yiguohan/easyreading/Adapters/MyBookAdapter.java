package com.yiguohan.easyreading.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.white.progressview.CircleProgressView;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.ViewImpls.DoubanBookDetailActivity;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/23.
 * Email:yiguohan@gmail.com
 */

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.ViewHolder>  {

    List<MyBook> myBooks;

    Context mContext;

    public MyBookAdapter(List<MyBook> myBooks) {
        this.myBooks = myBooks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_book_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.img_Cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBook myBook = myBooks.get(holder.getAdapterPosition());
                Intent intent = new Intent(EasyReadingApplication.getContext(), DoubanBookDetailActivity.class);
                intent.putExtra("BookId", String.valueOf(myBook.getBookId()));
                mContext.startActivity(intent);
            }
        });
        holder.circleImageView_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"打开数据统计页面",Toast.LENGTH_SHORT).show();
            }
        });
        holder.img_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"打开记录数据页面",Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"打开记录数据页面",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyBook myBook = myBooks.get(position);
        Glide.with(mContext).load(myBook.getImageUrl()).fitCenter().into(holder.img_Cover);
        holder.txt_Title.setText(myBook.getTitle());
        holder.txt_Pages.setText(myBook.getCurrentPage() + "/" + myBook.getTotalPage());
        holder.circleImageView_progress.setProgress((int) myBook.getProcess());
    }

    @Override
    public int getItemCount() {
        return myBooks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView img_Cover;
        ImageView img_More;
        TextView txt_Title;
        TextView txt_Pages;
        CircleProgressView circleImageView_progress;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView_book_item);
            img_Cover = (ImageView) itemView.findViewById(R.id.img_book_item);
            img_More = (ImageView) itemView.findViewById(R.id.img_more_book_item);
            txt_Title = (TextView) itemView.findViewById(R.id.txt_book_item);
            txt_Pages = (TextView) itemView.findViewById(R.id.txt_pages_book_item);
            circleImageView_progress = (CircleProgressView) itemView.findViewById(R.id.progressView_book_item);
        }
    }

}
