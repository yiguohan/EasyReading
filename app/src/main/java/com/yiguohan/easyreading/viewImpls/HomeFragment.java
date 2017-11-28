package com.yiguohan.easyreading.viewImpls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguohan.easyreading.adapters.HomeFragmentViewPagerAdapter;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private ViewPager viewPager;
    private HomeFragmentViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager)view.findViewById(R.id.home_viewPager);
        viewPagerAdapter = new HomeFragmentViewPagerAdapter(getChildFragmentManager(), Util.getBookTags());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout)view.findViewById(R.id.home_slidingTabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}

