package com.yiguohan.easyreading.ViewImpls;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.yiguohan.easyreading.Adapters.ThemeColorAdapter;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.Beans.ThemeColor;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment {


    private MainActivity activity;

    private ThemeColorAdapter themeColorAdapter;

    private List<ThemeColor> themeList = new ArrayList<ThemeColor>();

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
        initThemeList();

    }


    @OnClick(R.id.cv_select_theme)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_select_theme:
                initThemeSelector();
                break;
            case R.id.sw_night_mode:
                if (activity != null) {
                    if (ThemeUtil.getNightModeState()) {
                        ThemeUtil.setNightModeState(false);
                        activity.changeTheme(R.style.AppTheme);

                    } else {
                        ThemeUtil.setNightModeState(true);
                        activity.changeTheme(R.style.NightTheme);
                    }
                }
                break;
        }
    }

    private void initThemeSelector() {
        //TODO
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_theme_selector, null, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_themeselect);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(themeColorAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("选择主题").setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (activity != null) {
                    switch (ThemeUtil.getThemePosition()) {
                        case 0:
                            if (ThemeUtil.getNightModeState()) {
                                ThemeUtil.setNightModeState(false);
                            }
                            activity.changeTheme(R.style.AppTheme);
                            break;
//                        case 1:
//                            ThemeUtil.setNightModeState(false);
//                            activity.changeTheme(R.style.RedTheme);
//                            break;
                    }
                }
            }
        }).show();
    }

    /**
     * 初始化Theme数据
     */
    private void initThemeList() {
        themeColorAdapter = new ThemeColorAdapter();

        themeList.add(new ThemeColor(R.style.AppTheme));

        themeColorAdapter.setData(themeList);
        themeColorAdapter.notifyDataSetChanged();
    }
}
