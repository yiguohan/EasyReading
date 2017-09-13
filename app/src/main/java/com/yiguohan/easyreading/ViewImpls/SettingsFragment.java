package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.ThemeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {


    private MainActivity activity;

    @BindView(R.id.btn_night_mode)
    Button btnNightMode;
    @BindView(R.id.btn_change_theme)
    Button btnChangeTheme;
    @BindView(R.id.cv_select_theme)
    CardView cvSelectTheme;
    @BindView(R.id.sw_night_mode)
    Switch swNightMode;


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof MainActivity) {
            activity = (MainActivity) getActivity();
        }
        swNightMode.setChecked(ThemeUtil.getNightModeState());

    }


    @OnClick({R.id.btn_change_theme, R.id.cv_select_theme, R.id.sw_night_mode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_theme:
                Toast.makeText(EasyReadingApplication.getContext(), "更改主题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_select_theme:
                Toast.makeText(EasyReadingApplication.getContext(), "更改主题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sw_night_mode:
                if (activity != null) {
                    if (ThemeUtil.getNightModeState()) {
                        ThemeUtil.setNightModeState(false);
                        activity.changeTheme(R.style.DayTheme);

                    } else {
                        ThemeUtil.setNightModeState(true);
                        activity.changeTheme(R.style.NightTheme);
                    }
                }
                break;
        }
    }
}
