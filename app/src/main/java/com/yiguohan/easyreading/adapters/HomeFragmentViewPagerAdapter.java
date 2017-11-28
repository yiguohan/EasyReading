package com.yiguohan.easyreading.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yiguohan.easyreading.viewImpls.DoubanBooksFragment;
import com.yiguohan.easyreading.viewImpls.SearchBooksFragment;

import java.util.List;

/**
 * Created by yiguohan on 2017/5/18.
 * Email:yiguohan@gmail.com
 */

public class HomeFragmentViewPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private DoubanBooksFragment fragment;


    public HomeFragmentViewPagerAdapter(FragmentManager fm, List<String> titleList) {
        super(fm);
        titleList.add("搜索");
        this.titles = titleList.toArray(new String[titleList.size()]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == titles.length - 1) {
            return new SearchBooksFragment();
        } else {
            fragment = new DoubanBooksFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TAG", titles[position]);
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
