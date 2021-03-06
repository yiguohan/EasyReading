package com.yiguohan.easyreading.viewImpls;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yiguohan.easyreading.base.ActivityCollector;
import com.yiguohan.easyreading.base.BaseActivity;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.base.EasyReadingApplication;
import com.yiguohan.easyreading.presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.utils.StatusBarUtil;
import com.yiguohan.easyreading.utils.ThemeUtil;
import com.yiguohan.easyreading.utils.Util;
import com.yiguohan.easyreading.viewImpls.activities.LoginActivity;
import com.yiguohan.easyreading.viewImpls.fragments.AboutFragment;
import com.yiguohan.easyreading.viewImpls.fragments.CurrentReadingFragment;
import com.yiguohan.easyreading.viewImpls.fragments.HomeFragment;
import com.yiguohan.easyreading.viewImpls.fragments.SettingsFragment;
import com.yiguohan.easyreading.viewImpls.fragments.StaticsFragment;
import com.yiguohan.easyreading.views.IGetDataView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IGetDataView {

    private FragmentManager fragmentManager;

    private TextView textView;

    private DatabasePresenter databasePresenter;

    private List<BaseFragment> fragmentList;

    private static final int FRAGMENT_COUNT = 5;

    private static int theme = R.style.DayTheme;

    public int currentFragmentPosition = 0;

    private String toolBarTitle;

    @BindView(R.id.main_ToolBar)
    Toolbar toolbar;

    @BindView(R.id.main_DrawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.main_nav_View)
    NavigationView navigationView;

    @BindView(R.id.btn_Logout)
    Button btn_Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentFragmentPosition = savedInstanceState.getInt("currentFragmentPosition", 0);
            toolBarTitle = savedInstanceState.getString("toolBarTitle", "首页");
        }
        theme = ThemeUtil.getThemeColor();
        setTheme(theme);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        Util.setHelloSlogan(textView);
        StatusBarUtil.transParentStatusBar(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ThemeUtil.setThemeColor(theme);
        outState.putInt("currentFragmentPosition", currentFragmentPosition);
        outState.putString("toolBarTitle", toolBarTitle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = ThemeUtil.getThemeColor();
        currentFragmentPosition = savedInstanceState.getInt("currentFragmentPosition", 0);
        toolBarTitle = savedInstanceState.getString("toolBarTitle", "首页");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                replaceFragment(0);
                toolbar.setTitle("首页");
                toolBarTitle = "首页";
                break;
            case R.id.nav_book:
                replaceFragment(1);
                toolbar.setTitle("我的阅读");
                toolBarTitle = "我的阅读";
                break;
            case R.id.nav_statics:
                replaceFragment(2);
                toolbar.setTitle("阅读效率");
                toolBarTitle = "阅读效率";
                break;
            case R.id.nav_settings:
                replaceFragment(3);
                toolbar.setTitle("设置");
                toolBarTitle = "设置";
                break;
            case R.id.nav_about:
                replaceFragment(4);
                toolbar.setTitle("关于");
                toolBarTitle = "关于";
                break;
        }
        return true;
    }


    @Override
    public void getDataSuccess(Cursor cursor) {
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            Util.setHelloSlogan(textView, cursor.getString(cursor.getColumnIndex("account")));
        }
    }

    @Override
    public void getDataFail() {

    }

    /**
     * 绑定按钮监听
     *
     * @param view
     */
    @OnClick(R.id.btn_Logout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Logout:
                ActivityCollector.finishAll();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        if (!TextUtils.isEmpty(toolBarTitle)) {
            actionBar.setTitle(toolBarTitle);
        } else {
            actionBar.setTitle("首页");
        }
        //获取HeaderView 更改用户名（如果直接FindViewById会Crash）
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        textView = (TextView) headerView.findViewById(R.id.txt_nav_header_username);
        //设置初始菜单选择项和初始Fragment
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager = getSupportFragmentManager();
        replaceFragment(currentFragmentPosition);
    }

    /**
     * 初始化数据相关对象
     */
    private void initData() {
        databasePresenter = new DatabasePresenter(this);
        databasePresenter.getUserById(this, Integer.valueOf(EasyReadingApplication.getCurrentUserId()));
        initFragmentList();
    }

    /**
     * 初始化Fragment
     */
    private void initFragmentList() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<BaseFragment>();
        }
        fragmentList.add(new HomeFragment());
        fragmentList.add(new CurrentReadingFragment());
        fragmentList.add(new StaticsFragment());
        fragmentList.add(new SettingsFragment());
        fragmentList.add(new AboutFragment());
    }

    /**
     * 替换Fragment的方法
     *
     * @param fragment
     */
    private void replaceFragment(BaseFragment fragment) {
        mDrawerLayout.closeDrawers();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_FrameLayout, fragment)
                .commit();
    }

    /**
     * 根据index替换Fragment,同时实现了懒加载。
     *
     * @param index
     */
    private void replaceFragment(int index) {
        if (fragmentList == null) {
            initFragmentList();
        }
        if (fragmentList.size() < FRAGMENT_COUNT) {
            fragmentList = null;
            initFragmentList();
        }
        if (fragmentList.get(index) == null) {
            switch (index) {
                case 0:
                    fragmentList.add(0, new HomeFragment());
                    break;
                case 1:
                    fragmentList.add(1, new CurrentReadingFragment());
                    break;
                case 2:
                    fragmentList.add(2, new StaticsFragment());
                    break;
                case 3:
                    fragmentList.add(3, new SettingsFragment());
                    break;
                case 4:
                    fragmentList.add(4, new AboutFragment());
                    break;
                default:
                    break;
            }
        }
        replaceFragment(fragmentList.get(index));
        currentFragmentPosition = index;
    }

    public void changeTheme(int themeColor) {
        switch (themeColor) {
            case R.style.DayTheme:
                theme = R.style.DayTheme;
                recreate();
                break;
            case R.style.NightTheme:
                theme = R.style.NightTheme;
                recreate();
                break;
            default:
                this.theme = R.style.AppTheme;
                this.recreate();
        }
    }

}
