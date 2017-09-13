package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {

    @BindView(R.id.btn_night_mode)
    Button btnNightMode;
    @BindView(R.id.btn_change_theme)
    Button btnChangeTheme;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_night_mode, R.id.btn_change_theme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_night_mode:
                Toast.makeText(EasyReadingApplication.getContext(),"夜间模式",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_change_theme:
                Toast.makeText(EasyReadingApplication.getContext(),"更改主题",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
