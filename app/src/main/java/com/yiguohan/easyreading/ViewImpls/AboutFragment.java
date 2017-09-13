package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.UIComponents.StatisticsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

//    private StatisticsView statisticsView;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        statisticsView = (StatisticsView)view.findViewById(R.id.staticsView);
//        invalidate(statisticsView);
    }

    private void invalidate(StatisticsView view){
        view.setBottomStr(new String[]{"星期一，星期二，星期三，星期四，星期五，星期六，星期日"});
        view.setValues(new float[]{10f,90f,33f,66f,42f,99f,0f});
    }
}
