package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.yiguohan.easyreading.Utils.Util;
import com.yiguohan.easyreading.ViewImpls.CurrentReadingFragment;
import com.yiguohan.easyreading.ViewImpls.HomeFragment;
import com.yiguohan.easyreading.ViewImpls.StaticsFragment;
import com.yiguohan.easyreading.Views.IGetDataView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, IGetDataView {

    private FragmentManager fragmentManager;

    private TextView textView;



    private DatabasePresenter databasePresenter;

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
        setSupportActionBar(toolbar);
        toolbar.setTitle("首页");

        //获取HeaderView 更改用户名（如果直接FindViewById会Crash）
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        textView = (TextView) headerView.findViewById(R.id.txt_nav_header_username);
        ///// TODO: 2017/5/23 根据Id去SharedPreference中取账户名
        databasePresenter = new DatabasePresenter(this);
        databasePresenter.getUserById(this, Integer.valueOf(EasyReadingApplication.getCurrentUserId()));
        Util.setHelloSlogan(textView);

        btn_Logout.setOnClickListener(this);

        //设置初始菜单选择项和初始Fragment
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_FrameLayout, new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                replaceFragment(new HomeFragment());
                toolbar.setTitle("首页");
                break;
            case R.id.nav_book:
                replaceFragment(new CurrentReadingFragment());
                toolbar.setTitle("我的阅读");
                break;
            case R.id.nav_statics:
                replaceFragment(new StaticsFragment());
                toolbar.setTitle("阅读效率");
                break;
            case R.id.nav_about:
                replaceFragment(new AboutFragment());
                toolbar.setTitle("关于");
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Logout:
                ActivityCollector.finishAll();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
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
}
