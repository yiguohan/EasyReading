package com.yiguohan.easyreading.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.Util;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/19.
 * Email:yiguohan@gmail.com
 */

public class ReadingRecordAdapter extends RecyclerView.Adapter<ReadingRecordAdapter.ViewHolder> {

    List<ReadingRecord> readingRecords;

    public ReadingRecordAdapter(List<ReadingRecord> readingRecords) {
        this.readingRecords = readingRecords;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_TimeStamp;
        private TextView txt_CurrentPage;
        private TextView txt_Process;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_TimeStamp = (TextView)itemView.findViewById(R.id.txt_TimeStamp_ReadingRecordItem);
            txt_CurrentPage = (TextView)itemView.findViewById(R.id.txt_CurrentPage_ReadingRecordItem);
            txt_Process = (TextView)itemView.findViewById(R.id.txt_Process_ReadingRecordItem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reading_record_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReadingRecord readingRecord = readingRecords.get(position);
        holder.txt_TimeStamp.setText(Util.getFormatDate(readingRecord.getTimeStamp()));
        holder.txt_CurrentPage.setText(String.valueOf(readingRecord.getCurrentPage()));
        holder.txt_Process.setText("100%");
    }

    @Override
    public int getItemCount() {
        return readingRecords.size();
    }
}
