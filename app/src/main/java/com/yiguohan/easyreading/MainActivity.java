package com.yiguohan.easyreading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.Base.BaseFragment;
import com.yiguohan.easyreading.ViewImpls.CurrentReadingFragment;
import com.yiguohan.easyreading.ViewImpls.HomeFragment;
import com.yiguohan.easyreading.ViewImpls.StaticsFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    private DrawerLayout mDrawerLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.main_nav_View);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_FrameLayout,new HomeFragment()).commit();

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
     * @param fragment
     */
    private void replaceFragment(BaseFragment fragment){
        mDrawerLayout.closeDrawers();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_FrameLayout,fragment)
                .commit();
    }
}
