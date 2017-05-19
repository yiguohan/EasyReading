package com.yiguohan.easyreading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.ViewImpls.CurrentReadingFragment;
import com.yiguohan.easyreading.ViewImpls.HomeFragment;

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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mDrawerLayout.closeDrawers();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_FrameLayout, new HomeFragment())
                        .commit();
                break;
            case R.id.nav_book:
                mDrawerLayout.closeDrawers();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_FrameLayout,new CurrentReadingFragment())
                        .commit();
                break;
            case R.id.nav_statics:
                mDrawerLayout.closeDrawers();
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
}
