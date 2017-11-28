package com.yiguohan.easyreading.viewImpls;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yiguohan.easyreading.adapters.ReadingRecordAdapter;
import com.yiguohan.easyreading.base.BaseActivity;
import com.yiguohan.easyreading.beans.ReadingRecord;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.utils.Util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class CurrentReadingDetailActivity extends BaseActivity {

    SuitLines suitLines;

    private int curCount = 1;

    private float textSize = 8;

    private int[] color = {Color.RED, Color.GRAY, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};

    private ReadingRecordAdapter readingRecordAdapter;

    private RecyclerView recyclerView;

    private List<ReadingRecord> readingRecords = new ArrayList<ReadingRecord>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reading_detail);
        Util.setStatuBarTransparent(this);
        initData();
        suitLines = (SuitLines) findViewById(R.id.suitlines);
        recyclerView = (RecyclerView) findViewById(R.id.currentReadingDetail_RecyclerView);
        readingRecordAdapter = new ReadingRecordAdapter(readingRecords);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setAdapter(readingRecordAdapter);
        recyclerView.setLayoutManager(layoutManager);
        init(curCount);
    }

    public void init(int count) {
        if (count <= 0) {
            count = 0;
        }
        if (count == 1) {
            List<Unit> lines = new ArrayList<>();
//            for (int i = 0; i < 14; i++) {
//                lines.add(new Unit(new SecureRandom().nextInt(48), i + "d"));
//            }
            for (ReadingRecord r :
                    readingRecords) {

                lines.add(new Unit(r.getCurrentPage(), r.getTimeStamp().getDate() + "日"));
            }
            suitLines.feedWithAnim(lines);
            return;
        }

        SuitLines.LineBuilder builder = new SuitLines.LineBuilder();
        for (int j = 0; j < count; j++) {
            List<Unit> lines = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                lines.add(new Unit(new SecureRandom().nextInt(128), "" + i));
            }
            builder.add(lines, new int[]{color[new SecureRandom().nextInt(4)], color[new SecureRandom().nextInt(4)], color[new SecureRandom().nextInt(4)]});
        }
        builder.build(suitLines, true);

    }

    public void initData() {
        for (int i = 0; i < 140; i++) {
            ReadingRecord readingRecord = new ReadingRecord();
            readingRecord.setTimeStamp(new Date(2017, 05, 1 + i));
            readingRecord.setCurrentPage(new SecureRandom().nextInt(200));
            readingRecords.add(readingRecord);
        }
    }
}
