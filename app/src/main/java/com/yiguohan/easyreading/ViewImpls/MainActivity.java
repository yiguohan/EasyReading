package com.yiguohan.easyreading.ViewImpls;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.Presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.ViewImpls.CurrentReadingFragment;
import com.yiguohan.easyreading.ViewImpls.HomeFragment;
import com.yiguohan.easyreading.ViewImpls.StaticsFragment;
import com.yiguohan.easyreading.Views.IGetDataView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, IGetDataView {

    private FragmentManager fragmentManager;

    private TextView textView;

    @BindView(R.id.main_DrawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.main_nav_View)
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //获取HeaderView 更改用户名（如果直接FindViewById会Crash）
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        textView = (TextView) headerView.findViewById(R.id.txt_nav_header_username);
        ///// TODO: 2017/5/23 根据Id去SharedPreference中取账户名
        textView.setText("欢迎你，" + "账户" + "!");

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
                break;
            case R.id.nav_book:
                replaceFragment(new CurrentReadingFragment());
                break;
            case R.id.nav_statics:
                replaceFragment(new StaticsFragment());
                break;
            case R.id.nav_copyright:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_about:
                mDrawerLayout.closeDrawers();
                break;
        }
        return true;
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

    }

    @Override
    public void getDataFail() {

    }
}