package com.yiguohan.easyreading.viewImpls.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.adapters.ReadingRecordAdapter;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.beans.ReadingRecord;
import com.yiguohan.easyreading.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaticsFragment extends BaseFragment {


    SuitLines suitLines1;
    SuitLines suitLines2;
    SuitLines suitLines3;

    private int curCount = 1;

    private float textSize = 8;

    private int[] color = {Color.RED, Color.GRAY, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};

    private ReadingRecordAdapter readingRecordAdapter;

    private RecyclerView recyclerView;

    private List<ReadingRecord> readingRecords = new ArrayList<ReadingRecord>();

    public StaticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        suitLines1 = (SuitLines) view.findViewById(R.id.chart1_statics_fragment);
        suitLines2 = (SuitLines) view.findViewById(R.id.chart2_statics_fragment);
        suitLines3 = (SuitLines) view.findViewById(R.id.chart3_statics_fragment);
        init(suitLines1,curCount);
        init(suitLines2,curCount);
        init(suitLines3,curCount);
    }

    public void init(SuitLines chart,int count) {
        if (count <= 0) {
            count = 0;
        }
        if (count == 1) {
            List<Unit> lines = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                lines.add(new Unit(new SecureRandom().nextInt(48), i + "d"));
            }

            chart.feedWithAnim(lines);
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
        builder.build(chart, true);

    }

}
