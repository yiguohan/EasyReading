package com.yiguohan.easyreading.ViewImpls;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class MyBookDetailActivity extends BaseActivity {

    SuitLines suitLines;

    private int curCount = 1;

    private float textSize = 8;

    private int[] color = {Color.RED, Color.GRAY, 0xFFF76055, 0xFF9B3655, 0xFFF7A055};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_detail);
        suitLines = (SuitLines) findViewById(R.id.suitlines);
        init(curCount);
    }

    public void init(int count) {
        if (count <= 0) {
            count = 0;
        }
        if (count == 1) {
            List<Unit> lines = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                lines.add(new Unit(new SecureRandom().nextInt(48), i + "d"));
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
}
