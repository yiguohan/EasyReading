package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.ActivityCollector;
import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.Presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.StatusBarUtil;
import com.yiguohan.easyreading.Utils.ThemeUtil;
import com.yiguohan.easyreading.Utils.Util;
import com.yiguohan.easyreading.ViewImpls.CurrentReadingFragment;
import com.yiguohan.easyreading.ViewImpls.HomeFragment;
import com.yiguohan.easyreading.ViewImpls.StaticsFragment;
import com.yiguohan.easyreading.Views.IGetDataView;
import com.yiguohan.easyreading.Widgets.StatusBarView;

import org.w3c.dom.Text;

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
        Util.setHelloSlogan(textView);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, mDrawerLayout, ThemeUtil.getThemeColor());
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
                break;
            case R.id.nav_book:
                replaceFragment(1);
                toolbar.setTitle("我的阅读");
                break;
            case R.id.nav_statics:
                replaceFragment(2);
                toolbar.setTitle("阅读效率");
                break;
            case R.id.nav_settings:
                replaceFragment(3);
                toolbar.setTitle("设置");
            case R.id.nav_about:
                replaceFragment(4);
                toolbar.setTitle("关于");
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
        actionBar.setTitle("首页");
        //获取HeaderView 更改用户名（如果直接FindViewById会Crash）
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        textView = (TextView) headerView.findViewById(R.id.txt_nav_header_username);
        //设置初始菜单选择项和初始Fragment
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager = getSupportFragmentManager();
        replaceFragment(0);
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
        //向List中添加空对象，等到需要使用的时候再进行初始化
        for (int i = 0; i < 5; i++) {
            fragmentList.add(null);
        }
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
            return;
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
    }

}
